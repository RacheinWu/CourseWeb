package com.he.ssm.dao.other;

import com.he.ssm.bean.VideoBean;
import com.he.ssm.entity.other.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 视频(其他附件)Dao接口
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Mapper
public interface VideoDao{

    /**
     * 插入视频(其他附件)
     */
    int insert(Video video);

    /**
     * 删除视频(其他附件)
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 删除视频(其他附件)
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);

    /**
     * 更新视频(其他附件)
     */
    int updateByPrimaryKey(Video video);

    /**
     * 查询视频(其他附件)
     */
    Video selectByPrimaryKey(Long id);

    /**
     * 查询视频(其他附件)
     */
    List<VideoBean> findByCondition(Video video);

    /**
     * 查询视频(其他附件)
     */
    List<Video> findByAll(Video video);

    int updateStateById(@Param("updatedState") String updatedState, @Param("publishDate") Date publishDate, @Param("id") Long id);

    List<Video> findByStateOrderByPublishDateDesc(@Param("search") String search, @Param("state") String state);

    Video getByPublishDateLessThanOrderByPublishDateDesc(@Param("maxPublishDate") Date maxPublishDate);


    Video getByPublishDateGreaterThanOrderByPublishDate(@Param("minPublishDate") Date minPublishDate);

    @Select("update video set watchingCount=${count} where id = ${id}")
    void updateCountById(@Param("id") Long id, @Param("count") Long count);
}
