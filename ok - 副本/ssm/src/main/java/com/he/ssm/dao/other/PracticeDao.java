package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Practice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 社会实践(附件放在附件表中)Dao接口
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Mapper
public interface PracticeDao{

    /**
     * 插入社会实践(附件放在附件表中)
     */
    int insert(Practice practice);
    /**
     * 删除社会实践(附件放在附件表中)
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 删除社会实践(附件放在附件表中)
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);
    /**
     * 更新社会实践(附件放在附件表中)
     */
    int updateByPrimaryKey(Practice practice);
    /**
     * 查询社会实践(附件放在附件表中)
     */
    Practice selectByPrimaryKey(Long id);
    /**
     * 查询社会实践(附件放在附件表中)
     */
    List<Practice> findByCondition(Practice practice);
    /**
     * 查询社会实践(附件放在附件表中)
     */
    List<Practice> findByAll(Practice practice);
    int updateStateById(@Param("updatedState") String updatedState, @Param("publishDate") Date publishDate, @Param("id") Long id);

    List<Practice> findByStateOrderByPublishDateDesc(@Param("search")String search,@Param("state")String state);


    @Select("update practice set watchingCount=${count} where id = ${id}")
    void updateCountById(@Param("id") Long id, @Param("count") Long count);

}
