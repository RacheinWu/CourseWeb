package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Intro;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.Date;
import java.util.List;
/**
 * 介绍Dao接口
 *
 * @author 梁灿健
 * @date 2021-10-29 16:00:00
 */
@Mapper
public interface IntroDao {
    /**
     * 插入介绍
     */
    int insert(Intro intro);

    /**
     * 删除介绍
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 删除介绍
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);

    /**
     * 更新介绍
     */
    int updateByPrimaryKey(Intro intro);

    /**
     * 查询介绍
     */
    Intro selectByPrimaryKey(Intro intro);

    /**
     * 查询介绍
     */
    List<Intro> findByCondition(Intro intro);

    @Select("update attach set watchingCount=${count} where id = ${id}")
    void updateCountById(@Param("id") Long id, @Param("count") Long count);

    @Select("select * from intro where id = ${id}")
    Intro selectById(@Param("id") Long id);
}


