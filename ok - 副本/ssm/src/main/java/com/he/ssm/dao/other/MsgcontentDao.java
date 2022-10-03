package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Msgcontent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 留言内容Dao接口
 * @author itaem
 * @date 2021-03-21 14:52:17
 */
@Mapper
public interface MsgcontentDao{

    /**
     * 插入留言内容
     */
    int insert(Msgcontent msgcontent);
    /**
     * 删除留言内容
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 删除留言内容
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);
    /**
     * 更新留言内容
     */
    int updateByPrimaryKey(Msgcontent msgcontent);
    /**
     * 查询留言内容
     */
    Msgcontent selectByPrimaryKey(Long id);
    /**
     * 查询留言内容
     */
    List<Msgcontent> findByCondition(Msgcontent msgcontent);
    /**
     * 查询留言内容
     */
    List<Msgcontent> findUnique(Msgcontent msgcontent);
    int updateStateById(@Param("updatedState")String updatedState,@Param("id")Long id);
    List<Msgcontent> findByTitleIdAndState(@Param("titleId")Long titleId,@Param("state")String state);




}
