package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.dao.other.UsersDao;
import com.he.ssm.entity.other.Users;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户Service业务层处理
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class UsersService {
    @Resource
    private UsersDao usersDao;
    /**
     * 查询所有用户
     */
    public ResultT<List<Users>> list(Users users) {
        List<Users> list = this.usersDao.findByAll(users);
        return ResultT.successWithData(list);
    }
    /**
     * 分页显示用户
     */
    public ResultT<PageInfo<Users>> page(Users users, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Users> list = this.usersDao.findByCondition(users);
        PageInfo<Users> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增用户
     */
    public ResultT<Void> add(Users users) {
        //判断数据唯一性
        Users params = new Users();
        params.setUserType(users.getUserType());
        params.setAccount(users.getAccount());
        List<Users> uniqueData = this.usersDao.findByAll(params);
        if (!uniqueData.isEmpty()) {
            return ResultT.errorWithMsg("数据已存在");
        }
        users.setState("valid");
        users.setCreateDate(new Date());
        int count = this.usersDao.insert(users);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改用户
     */
    public ResultT<Void> update(Users users) {
        Long id = users.getId();
        //判断数据唯一性
        Users params = new Users();
        params.setUserType(users.getUserType());
        params.setAccount(users.getAccount());
        List<Users> uniqueData = this.usersDao.findByAll(params);
        boolean check = uniqueData.size() > 1 || (uniqueData.size() == 1 && !uniqueData.get(0).getId().equals(id));
        if (check) {
            return ResultT.errorWithMsg("数据已存在");
        }
        Users existData = this.usersDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(users, existData);
        int count = this.usersDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 删除用户
     */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.usersDao.deleteByPrimaryKeyIn(idList);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

}
