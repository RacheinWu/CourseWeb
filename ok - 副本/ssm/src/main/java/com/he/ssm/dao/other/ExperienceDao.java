package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Experience;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 经验Dao接口
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Mapper
public interface ExperienceDao{

    /**
     * 插入经验
     */
    int insert(Experience experience);
    /**
     * 删除经验
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 删除经验
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);
    /**
     * 更新经验
     */
    int updateByPrimaryKey(Experience experience);
    /**
     * 查询经验
     */
    Experience selectByPrimaryKey(Long id);
    /**
     * 查询经验
     */
    List<Experience> findByCondition(Experience experience);
    /**
     * 查询经验
     */
    List<Experience> findByAll(Experience experience);
    int updateStateById(@Param("updatedState") String updatedState, @Param("publishDate") Date publishDate, @Param("id") Long id);
    Experience getByPublishDateLessThanOrderByPublishDateDesc(@Param("maxPublishDate")Date maxPublishDate);
    Experience getByPublishDateGreaterThanOrderByPublishDate(@Param("minPublishDate")Date minPublishDate);
    List<Experience> findByStateOrderByPublishDateDesc(@Param("search")String search,@Param("state")String state);







}
