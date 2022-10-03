package com.he.ssm.dao.other;

import com.he.ssm.entity.other.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 新闻Dao接口
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Mapper
public interface NewsDao {

    /**
     * 插入新闻
     */
    int insert(News news);

    /**
     * 删除新闻
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 删除新闻
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);

    /**
     * 更新新闻
     */
    int updateByPrimaryKey(News news);

    /**
     * 查询新闻
     */
    News selectByPrimaryKey(Long id);

    /**
     * 查询新闻
     */
    List<News> findByCondition(News news);

    /**
     * 查询新闻
     */
    List<News> findByAll(News news);

    int updateStateById(@Param("updatedState") String updatedState, @Param("publishDate") Date publishDate, @Param("id") Long id);
    List<News> findByStateOrderByPublishDateDesc(@Param("search")String search,@Param("state")String state);


}
