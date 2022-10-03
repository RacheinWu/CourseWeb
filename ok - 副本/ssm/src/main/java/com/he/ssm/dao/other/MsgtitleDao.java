package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Msgtitle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 留言标题Dao接口
 * @author itaem
 * @date 2021-03-21 14:52:17
 */
@Mapper
public interface MsgtitleDao{

    /**
     * 插入留言标题
     */
    int insert(Msgtitle msgtitle);
    /**
     * 删除留言标题
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 删除留言标题
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);
    /**
     * 更新留言标题
     */
    int updateByPrimaryKey(Msgtitle msgtitle);
    /**
     * 查询留言标题
     */
    Msgtitle selectByPrimaryKey(Long id);
    /**
     * 查询留言标题
     */
    List<Msgtitle> findByCondition(Msgtitle msgtitle);
    /**
     * 查询留言标题
     */
    List<Msgtitle> findUnique(Msgtitle msgtitle);

    int updateStateById(@Param("updatedState")String updatedState,@Param("id")Long id);
    List<Msgtitle> findByState(@Param("state")String state);






}
