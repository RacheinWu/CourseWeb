package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.PracticeDao;
import com.he.ssm.entity.other.Practice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 社会实践(附件放在附件表中)Service业务层处理
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class PracticeService{
    @Resource
    private UploadConfig uploadConfig;
    @Resource
    private PracticeDao  practiceDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Practice>> list(Practice practice) {
        List<Practice> list = this.practiceDao.findByAll(practice);
        return ResultT.successWithData(list);
    }
    /**
     * 分页显示社会实践(附件放在附件表中)
     */
    public ResultT<PageInfo<Practice>> page(Practice practice,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(Objects.nonNull(practice.getBeginPublishDate())){
            practice.setBeginPublishDate(DateUtil.beginOfDay( practice.getBeginPublishDate()));
        }
        if(Objects.nonNull(practice.getEndPublishDate())){
            practice.setEndPublishDate(DateUtil.endOfDay( practice.getEndPublishDate()));
        }
        List<Practice> list=this.practiceDao.findByCondition(practice);
        for (Practice b : list) {
            b.setPhotoUrl(uploadConfig.getHttpPrefix() + b.getPhotoUrl());
        }
        PageInfo<Practice> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增社会实践(附件放在附件表中)
     */
    public ResultT<Void> add(Practice practice){
        practice.setCreateDate( new Date());
        if ("发布".equals(practice.getState())) {
            practice.setPublishDate(new Date());
        }
        int count = this.practiceDao.insert(practice);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改社会实践(附件放在附件表中)
     */
    public ResultT<Void> update(Practice practice) {
        Long id = practice.getId();
        String photoUrl = practice.getPhotoUrl();
        String httpPrefix = uploadConfig.getHttpPrefix();
        if (StringUtils.isNotBlank(photoUrl) && photoUrl.startsWith(httpPrefix)) {
            practice.setPhotoUrl(photoUrl.replace(httpPrefix, ""));
        }
        Date now = new Date();
        if ("未发布".equals(practice.getState())) {
            now = null;
        }
        practice.setPublishDate(now);
        Practice existData = this.practiceDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(practice, existData);
        int count = this.practiceDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
    * 删除社会实践(附件放在附件表中)
    */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.practiceDao.deleteByPrimaryKeyIn(idList);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }
    public ResultT<Void> updateState(Long id, String state) {
        Date now = new Date();
        if ("未发布".equals(state)) {
            now = null;
        }
        int count = this.practiceDao.updateStateById(state, now, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

}
