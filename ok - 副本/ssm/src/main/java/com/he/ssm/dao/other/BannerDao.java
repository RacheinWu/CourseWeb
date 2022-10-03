package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 轮播图Dao接口
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Mapper
public interface BannerDao {

    /**
     * 插入轮播图
     */
    int insert(Banner banner);

    /**
     * 删除轮播图
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 删除轮播图
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);

    /**
     * 更新轮播图
     */
    int updateByPrimaryKey(Banner banner);

    /**
     * 查询轮播图
     */
    Banner selectByPrimaryKey(Long id);

    /**
     * 查询轮播图
     */
    List<Banner> findByCondition(Banner banner);

    /**
     * 查询轮播图
     */
    List<Banner> findByAll(Banner banner);

    int updateStateById(@Param("updatedState") String updatedState, @Param("publishDate") Date publishDate, @Param("id") Long id);

    List<Banner> findByStateOrderByOrderNumDesc(@Param("state")String state);



}
