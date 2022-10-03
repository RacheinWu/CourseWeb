package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Cases;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 案例Dao接口
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Mapper
public interface CasesDao{

    /**
     * 插入案例
     */
    int insert(Cases cases);
    /**
     * 删除案例
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 删除案例
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);
    /**
     * 更新案例
     */
    int updateByPrimaryKey(Cases cases);
    /**
     * 查询案例
     */
    Cases selectByPrimaryKey(Long id);
    /**
     * 查询案例
     */
    List<Cases> findByCondition(Cases cases);
    /**
     * 查询案例
     */
    List<Cases> findByAll(Cases cases);
    int updateStateById(@Param("updatedState") String updatedState, @Param("publishDate") Date publishDate, @Param("id") Long id);
    List<Cases> findByStateOrderByPublishDateDesc(@Param("search")String search,@Param("state")String state);
    Cases getByPublishDateLessThanOrderByPublishDateDesc(@Param("maxPublishDate")Date maxPublishDate);


    Cases getByPublishDateGreaterThanOrderByPublishDate(@Param("minPublishDate")Date minPublishDate);




}
