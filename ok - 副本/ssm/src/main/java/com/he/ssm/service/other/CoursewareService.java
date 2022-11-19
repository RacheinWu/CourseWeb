package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.CoursewareReqBean;
import com.he.ssm.bean.FileInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.AttachDao;
import com.he.ssm.dao.other.CoursewareDao;
import com.he.ssm.entity.other.Attach;
import com.he.ssm.entity.other.Courseware;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 课件(其他附件在附件表中)Service业务层处理
 *
 * @author itaem
 * @date 2021-03-08 23:47:15
 */
@Service
public class CoursewareService {
    @Resource
    private UploadConfig  uploadConfig;
    @Resource
    private CoursewareDao coursewareDao;
    @Resource
    private AttachDao     attachDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Courseware>> list(Courseware courseware) {
        List<Courseware> list = this.coursewareDao.findByAll(courseware);
        return ResultT.successWithData(list);
    }

    /**
     * 分页显示课件(其他附件在附件表中)
     */
    public ResultT<PageInfo<CoursewareReqBean>> page(Courseware courseware, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (Objects.nonNull(courseware.getBeginPublishDate())) {
            courseware.setBeginPublishDate(DateUtil.beginOfDay(courseware.getBeginPublishDate()));
        }
        if (Objects.nonNull(courseware.getEndPublishDate())) {
            courseware.setEndPublishDate(DateUtil.endOfDay(courseware.getEndPublishDate()));
        }
        List<CoursewareReqBean> list = this.coursewareDao.findByCondition(courseware);
        for (CoursewareReqBean b : list) {
            b.setCoursewareUrl(uploadConfig.getHttpPrefix() + b.getCoursewareUrl());
            Long dataId = b.getId();
            List<FileInfo> fileInfoList = new ArrayList<>();
            List<Attach> attachList = this.attachDao.findByDataIdAndAttachType(dataId, "courseware");
            for (Attach attach : attachList) {
                FileInfo fileInfo = BeanUtil.toBean(attach, FileInfo.class);
                fileInfo.setNetworkPath(uploadConfig.getCtx() + uploadConfig.getDirResourcePath() + "/" + attach.getRelativePath());
                fileInfoList.add(fileInfo);
            }
            b.setUploadFileList(fileInfoList);
        }
        PageInfo<CoursewareReqBean> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增课件(其他附件在附件表中)
     */
    public ResultT<Void> add(CoursewareReqBean req) {
        //判断数据唯一性
        Courseware params = new Courseware();
        params.setCoursewareType(req.getCoursewareType());
        params.setTile(req.getTile());
        params.setAuthor(req.getAuthor());
        List<Courseware> uniqueData = this.coursewareDao.findByAll(params);
        if (!uniqueData.isEmpty()) {
            return ResultT.errorWithMsg("数据已存在");
        }
        if ("发布".equals(req.getState())) {
            req.setPublishDate(new Date());
        }
        req.setCoursewareUrl(req.getUploadFileList().get(0).getRelativePath());
        req.setCreateDate(new Date());
        int count = this.coursewareDao.insert(req);
        if (count > 0) {
            List<FileInfo> uploadFileList = req.getUploadFileList();
            if (Objects.nonNull(uploadFileList) && !uploadFileList.isEmpty()) {
                Long dataId = req.getId();
                List<Attach> attachList = new ArrayList<>();
                for (FileInfo fileInfo : uploadFileList) {
                    Attach attach = BeanUtil.toBean(fileInfo, Attach.class);
                    attach.setDataId(dataId);
                    attach.setAttachType("courseware");
                    attachList.add(attach);
                }
                this.attachDao.batchInsert(attachList);
            }
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改课件(其他附件在附件表中)
     */
    public ResultT<Void> update(CoursewareReqBean req) {
        Long id = req.getId();
        //判断数据唯一性
        Courseware params = new Courseware();
        params.setCoursewareType(req.getCoursewareType());
        params.setTile(req.getTile());
        params.setAuthor(req.getAuthor());
        List<Courseware> uniqueData = this.coursewareDao.findByAll(params);
        boolean check = uniqueData.size() > 1 || (uniqueData.size() == 1 && !uniqueData.get(0).getId().equals(id));
        if (check) {
            return ResultT.errorWithMsg("数据已存在");
        }
        String photoUrl = req.getCoursewareUrl();
        String httpPrefix = uploadConfig.getHttpPrefix();
        if (StringUtils.isNotBlank(photoUrl) && photoUrl.startsWith(httpPrefix)) {
            req.setCoursewareUrl(photoUrl.replace(httpPrefix, ""));
        }
        Date now = new Date();
        if ("未发布".equals(req.getState())) {
            now = null;
        }
        req.setPublishDate(now);
        Courseware existData = this.coursewareDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(req, existData);
        int count = this.coursewareDao.updateByPrimaryKey(existData);
        if (count > 0) {
            List<Long> delIdList = req.getDelIdList();
            if (Objects.nonNull(delIdList) && !delIdList.isEmpty()) {
                List<Attach> attachList = this.attachDao.findByIdIn(delIdList);
                for (Attach attach : attachList) {
                    FileUtil.del(uploadConfig.getAbsolutePrefix() + attach.getRelativePath());
                }
                this.attachDao.deleteByIdIn(delIdList);
            }
            Long dataId = req.getId();
            List<FileInfo> uploadFileList = req.getUploadFileList();
            if (Objects.nonNull(uploadFileList) && !uploadFileList.isEmpty()) {
                List<Attach> attachList = new ArrayList<>();
                for (FileInfo fileInfo : uploadFileList) {
                    Attach attach = BeanUtil.toBean(fileInfo, Attach.class);
                    attach.setDataId(dataId);
                    attach.setAttachType("courseware");
                    attachList.add(attach);
                }
                this.attachDao.batchInsert(attachList);
            }
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 删除课件(其他附件在附件表中)
     */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.coursewareDao.deleteByPrimaryKeyIn(idList);
        if (count > 0) {
            this.attachDao.deleteByDataIdIn(idList);
            return ResultT.success();
        }
        return ResultT.error();
    }

    public ResultT<Void> updateState(Long id, String state) {
        Date now = new Date();
        if ("未发布".equals(state)) {
            now = null;
        }
        int count = this.coursewareDao.updateStateById(state, now, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }
}
