package com.he.ssm.dao.other;

import com.he.ssm.entity.other.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 用户Dao接口
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Mapper
public interface UsersDao{

    /**
     * 插入用户
     */
    int insert(Users users);
    /**
     * 删除用户
     */
    int deleteByPrimaryKey(Long id);
    /**
     * 删除用户
     */
    int deleteByPrimaryKeyIn(@Param("idList") Collection<Long> idList);
    /**
     * 更新用户
     */
    int updateByPrimaryKey(Users users);
    /**
     * 查询用户
     */
    Users selectByPrimaryKey(Long id);
    /**
     * 查询用户
     */
    List<Users> findByCondition(Users users);
    /**
     * 查询用户
     */
    List<Users> findByAll(Users users);

    Users getByAccountAndPwdAndUserType(@Param("account")String account,@Param("pwd")String pwd,@Param("userType")String userType);




}
