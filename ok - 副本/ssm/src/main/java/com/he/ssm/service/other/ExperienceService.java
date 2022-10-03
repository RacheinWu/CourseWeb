package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.ExperienceDao;
import com.he.ssm.entity.other.Experience;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 经验Service业务层处理
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class ExperienceService{
    @Resource
    private UploadConfig  uploadConfig;
    @Resource
    private ExperienceDao experienceDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Experience>> list(Experience experience) {
        List<Experience> list = this.experienceDao.findByAll(experience);
        return ResultT.successWithData(list);
    }
    /**
     * 分页显示经验
     */
    public ResultT<PageInfo<Experience>> page(Experience experience,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(Objects.nonNull(experience.getBeginPublishDate())){
            experience.setBeginPublishDate(DateUtil.beginOfDay( experience.getBeginPublishDate()));
        }
        if(Objects.nonNull(experience.getEndPublishDate())){
            experience.setEndPublishDate(DateUtil.endOfDay( experience.getEndPublishDate()));
        }
        List<Experience> list=this.experienceDao.findByCondition(experience);
        for (Experience b : list) {
            b.setPhotoUrl(uploadConfig.getHttpPrefix() + b.getPhotoUrl());
        }
        PageInfo<Experience> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增经验
     */
    public ResultT<Void> add(Experience experience){
        //判断数据唯一性
        Experience params=new Experience();
        params.setTitle(experience.getTitle());
        params.setAuthor(experience.getAuthor());
        List<Experience> uniqueData = this.experienceDao.findByAll(params);
        if (!uniqueData.isEmpty()) {
            return ResultT.errorWithMsg("数据已存在");
        }
        if ("发布".equals(experience.getState())) {
            experience.setPublishDate(new Date());
        }
        Date now = new Date();
        experience.setCreateDate(now);

        int count = this.experienceDao.insert(experience);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }


    /**
     * 修改经验
     */
    public ResultT<Void> update(Experience experience) {
        Long id = experience.getId();
        //判断数据唯一性
        Experience params=new Experience();
        params.setTitle(experience.getTitle());
        params.setAuthor(experience.getAuthor());
        List<Experience> uniqueData = this.experienceDao.findByAll(params);
        boolean check = uniqueData.size() > 1 || (uniqueData.size() == 1 && !uniqueData.get(0).getId().equals(id));
        if (check) {
            return ResultT.errorWithMsg("数据已存在");
        }
        String photoUrl = experience.getPhotoUrl();
        String httpPrefix = uploadConfig.getHttpPrefix();
        if (StringUtils.isNotBlank(photoUrl) && photoUrl.startsWith(httpPrefix)) {
            experience.setPhotoUrl(photoUrl.replace(httpPrefix, ""));
        }
        Date now = new Date();
        if ("未发布".equals(experience.getState())) {
            now = null;
        }
        experience.setPublishDate(now);
        Experience existData = this.experienceDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(experience, existData);
        int count = this.experienceDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
    * 删除经验
    */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.experienceDao.deleteByPrimaryKeyIn(idList);
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
        int count = this.experienceDao.updateStateById(state, now, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

}
