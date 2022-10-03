package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Policy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 政策Dao接口
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Mapper
public interface PolicyDao{

    /**
     * 插入政策
     */
    int insert(Policy policy);
    /**
     * 删除政策
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 删除政策
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);
    /**
     * 更新政策
     */
    int updateByPrimaryKey(Policy policy);
    /**
     * 查询政策
     */
    Policy selectByPrimaryKey(Long id);
    /**
     * 查询政策
     */
    List<Policy> findByCondition(Policy policy);
    /**
     * 查询政策
     */
    List<Policy> findByAll(Policy policy);

    int updateStateById(@Param("updatedState") String updatedState, @Param("publishDate") Date publishDate, @Param("id") Long id);
    List<Policy> findByStateOrderByPublishDateDesc(@Param("search")String search,@Param("state")String state);


}
