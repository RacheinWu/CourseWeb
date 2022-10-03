package com.he.ssm.dao.other;

import com.he.ssm.bean.CoursewareReqBean;
import com.he.ssm.entity.other.Courseware;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 课件(其他附件在附件表中)Dao接口
 *
 * @author itaem
 * @date 2021-03-08 23:47:15
 */
@Mapper
public interface CoursewareDao {

    /**
     * 插入课件(其他附件在附件表中)
     */
    int insert(Courseware courseware);

    /**
     * 删除课件(其他附件在附件表中)
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 删除课件(其他附件在附件表中)
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);

    /**
     * 更新课件(其他附件在附件表中)
     */
    int updateByPrimaryKey(Courseware courseware);

    /**
     * 查询课件(其他附件在附件表中)
     */
    Courseware selectByPrimaryKey(Long id);

    /**
     * 查询课件(其他附件在附件表中)
     */
    List<CoursewareReqBean> findByCondition(Courseware courseware);

    /**
     * 查询课件(其他附件在附件表中)
     */
    List<Courseware> findByAll(Courseware courseware);

    int updateStateById(@Param("updatedState") String updatedState, @Param("publishDate") Date publishDate, @Param("id") Long id);

    List<Courseware> findByStateOrderByPublishDateDesc(@Param("search") String search, @Param("state") String state);

    Courseware getByPublishDateLessThanOrderByPublishDateDesc(@Param("maxPublishDate") Date maxPublishDate);


    Courseware getByPublishDateGreaterThanOrderByPublishDate(@Param("minPublishDate") Date minPublishDate);


}
