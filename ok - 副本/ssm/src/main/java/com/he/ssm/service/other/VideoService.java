package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.FileInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.bean.VideoBean;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.AttachDao;
import com.he.ssm.dao.other.VideoDao;
import com.he.ssm.entity.other.Attach;
import com.he.ssm.entity.other.Video;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 视频(其他附件)Service业务层处理
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class VideoService {
    @Resource
    private UploadConfig uploadConfig;
    @Resource
    private VideoDao     videoDao;
    @Resource
    private AttachDao    attachDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Video>> list(Video video) {
        List<Video> list = this.videoDao.findByAll(video);
        return ResultT.successWithData(list);
    }

    /**
     * 分页显示视频(其他附件)
     */
    public ResultT<PageInfo<VideoBean>> page(Video video, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (Objects.nonNull(video.getBeginPublishDate())) {
            video.setBeginPublishDate(DateUtil.beginOfDay(video.getBeginPublishDate()));
        }
        if (Objects.nonNull(video.getEndPublishDate())) {
            video.setEndPublishDate(DateUtil.endOfDay(video.getEndPublishDate()));
        }
        List<VideoBean> list = this.videoDao.findByCondition(video);
        for (VideoBean b : list) {
            b.setPhotoUrl(uploadConfig.getHttpPrefix() + b.getPhotoUrl());
            b.setVideoUrl(uploadConfig.getHttpPrefix() + b.getVideoUrl());
            Long dataId = b.getId();
            List<FileInfo> fileInfoList = new ArrayList<>();
            List<Attach> attachList = this.attachDao.findByDataIdAndAttachType(dataId, "video");
            for (Attach attach : attachList) {
                FileInfo fileInfo = BeanUtil.toBean(attach, FileInfo.class);
                fileInfo.setNetworkPath(uploadConfig.getCtx() + uploadConfig.getDirResourcePath() + "/" + attach.getRelativePath());
                fileInfoList.add(fileInfo);
            }
            b.setUploadFileList(fileInfoList);
        }
        PageInfo<VideoBean> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增视频(其他附件)
     */
    public ResultT<Void> add(VideoBean req) {
        //判断数据唯一性
        Video params = new Video();
        params.setVideoType(req.getVideoType());
        params.setTitle(req.getTitle());
        params.setTeacher(req.getTeacher());
        List<Video> uniqueData = this.videoDao.findByAll(params);
        if (!uniqueData.isEmpty()) {
            return ResultT.errorWithMsg("数据已存在");
        }
        if ("发布".equals(req.getState())) {
            req.setPublishDate(new Date());
        }
        req.setCreateDate(new Date());
        int count = this.videoDao.insert(req);
        if (count > 0) {
            List<FileInfo> uploadFileList = req.getUploadFileList();
            if (Objects.nonNull(uploadFileList) && !uploadFileList.isEmpty()) {
                Long dataId = req.getId();
                List<Attach> attachList = new ArrayList<>();
                for (FileInfo fileInfo : uploadFileList) {
                    Attach attach = BeanUtil.toBean(fileInfo, Attach.class);
                    attach.setDataId(dataId);
                    attach.setAttachType("video");
                    attachList.add(attach);
                }
                this.attachDao.batchInsert(attachList);
            }
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改视频(其他附件)
     */
    public ResultT<Void> update(VideoBean req) {
        Long id = req.getId();
        //判断数据唯一性
        Video params = new Video();
        params.setVideoType(req.getVideoType());
        params.setTitle(req.getTitle());
        params.setTeacher(req.getTeacher());
        List<Video> uniqueData = this.videoDao.findByAll(params);
        boolean check = uniqueData.size() > 1 || (uniqueData.size() == 1 && !uniqueData.get(0).getId().equals(id));
        if (check) {
            return ResultT.errorWithMsg("数据已存在");
        }
        String photoUrl = req.getPhotoUrl();
        String videoUrl = req.getVideoUrl();

        String httpPrefix = uploadConfig.getHttpPrefix();
        if (StringUtils.isNotBlank(photoUrl) && photoUrl.startsWith(httpPrefix)) {
            req.setPhotoUrl(photoUrl.replace(httpPrefix, ""));
        }
        if (StringUtils.isNotBlank(videoUrl) && videoUrl.startsWith(httpPrefix)) {
            req.setVideoUrl(videoUrl.replace(httpPrefix, ""));
        }
        Date now = new Date();
        if ("未发布".equals(req.getState())) {
            now = null;
        }
        req.setPublishDate(now);
        Video existData = this.videoDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(req, existData);
        int count = this.videoDao.updateByPrimaryKey(existData);
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
                    attach.setAttachType("video");
                    attachList.add(attach);
                }
                this.attachDao.batchInsert(attachList);
            }
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 删除视频(其他附件)
     */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.videoDao.deleteByPrimaryKeyIn(idList);
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
        int count = this.videoDao.updateStateById(state, now, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    public void test() {
        System.out.println(Objects.isNull(attachDao));
    }

}
