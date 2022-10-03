package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.TeacherDao;
import com.he.ssm.entity.other.Teacher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 教师Service业务层处理
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class TeacherService{
    @Resource
    private UploadConfig uploadConfig;
    @Resource
    private TeacherDao teacherDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Teacher>> list(Teacher teacher) {
        List<Teacher> list = this.teacherDao.findByAll(teacher);
        return ResultT.successWithData(list);
    }
    /**
     * 分页显示教师
     */
    public ResultT<PageInfo<Teacher>> page(Teacher teacher,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(Objects.nonNull(teacher.getBeginPublishDate())){
            teacher.setBeginPublishDate(DateUtil.beginOfDay( teacher.getBeginPublishDate()));
        }
        if(Objects.nonNull(teacher.getEndPublishDate())){
            teacher.setEndPublishDate(DateUtil.endOfDay( teacher.getEndPublishDate()));
        }
        List<Teacher> list=this.teacherDao.findByCondition(teacher);
        for (Teacher b : list) {
            b.setPhotoUrl(uploadConfig.getHttpPrefix() + b.getPhotoUrl());
        }
        PageInfo<Teacher> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增教师
     */
    public ResultT<Void> add(Teacher teacher){
        teacher.setCreateDate( new Date());
        if ("发布".equals(teacher.getState())) {
            teacher.setPublishDate(new Date());
        }
        int count = this.teacherDao.insert(teacher);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改教师
     */
    public ResultT<Void> update(Teacher teacher) {
        Long id = teacher.getId();
        String photoUrl = teacher.getPhotoUrl();
        String httpPrefix = uploadConfig.getHttpPrefix();
        if (StringUtils.isNotBlank(photoUrl) && photoUrl.startsWith(httpPrefix)) {
            teacher.setPhotoUrl(photoUrl.replace(httpPrefix, ""));
        }
        Date now = new Date();
        if ("未发布".equals(teacher.getState())) {
            now = null;
        }
        teacher.setPublishDate(now);
        Teacher existData = this.teacherDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(teacher, existData);
        int count = this.teacherDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
    * 删除教师
    */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.teacherDao.deleteByPrimaryKeyIn(idList);
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
        int count = this.teacherDao.updateStateById(state, now, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

}
