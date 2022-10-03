package com.he.ssm.dao.other;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

import com.he.ssm.entity.other.Test;

/**
 * 测试代码生成Dao接口
 * @author itaem
 * @date 2021-03-18 11:18:17
 */
@Mapper
public interface TestDao{

    /**
     * 插入测试代码生成
     */
    int insert(Test test);
    /**
     * 删除测试代码生成
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 删除测试代码生成
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);
    /**
     * 更新测试代码生成
     */
    int updateByPrimaryKey(Test test);
    /**
     * 查询测试代码生成
     */
    Test selectByPrimaryKey(Long id);
    /**
     * 查询测试代码生成
     */
    List<Test> findByCondition(Test test);
    /**
     * 查询测试代码生成
     */
    List<Test> findUnique(Test test);


}
