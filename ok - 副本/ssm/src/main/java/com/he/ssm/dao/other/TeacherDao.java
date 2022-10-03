package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 教师Dao接口
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Mapper
public interface TeacherDao{

    /**
     * 插入教师
     */
    int insert(Teacher teacher);
    /**
     * 删除教师
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 删除教师
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);
    /**
     * 更新教师
     */
    int updateByPrimaryKey(Teacher teacher);
    /**
     * 查询教师
     */
    Teacher selectByPrimaryKey(Long id);
    /**
     * 查询教师
     */
    List<Teacher> findByCondition(Teacher teacher);
    /**
     * 查询教师
     */
    List<Teacher> findByAll(Teacher teacher);
    int updateStateById(@Param("updatedState") String updatedState, @Param("publishDate") Date publishDate, @Param("id") Long id);
    List<Teacher> findByStateOrderByPublishDateDesc(@Param("search")String search,@Param("state")String state,@Param("honor")String honor);




}
