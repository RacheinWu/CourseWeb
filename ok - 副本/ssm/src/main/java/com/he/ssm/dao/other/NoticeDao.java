package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 公告Dao接口
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Mapper
public interface NoticeDao{

    /**
     * 插入公告
     */
    int insert(Notice notice);
    /**
     * 删除公告
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 删除公告
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);
    /**
     * 更新公告
     */
    int updateByPrimaryKey(Notice notice);
    /**
     * 查询公告
     */
    Notice selectByPrimaryKey(Long id);
    /**
     * 查询公告
     */
    List<Notice> findByCondition(Notice notice);
    /**
     * 查询公告
     */
    List<Notice> findByAll(Notice notice);
    int updateStateById(@Param("updatedState") String updatedState, @Param("publishDate") Date publishDate, @Param("id") Long id);
    List<Notice> findByStateOrderByPublishDateDesc(@Param("search")String search,@Param("state")String state);



}
