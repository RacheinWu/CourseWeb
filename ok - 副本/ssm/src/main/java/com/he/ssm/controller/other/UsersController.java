package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Users;
import com.he.ssm.service.other.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Controller
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Api(tags = "用户接口")
@RestController
public class UsersController{

    @Resource
    private UsersService usersService;

    /**
     * 查询所有用户
     */
    @ApiOperation("查询所有用户")
    @GetMapping("/other/users/list")
    public ResultT<List<Users>> list(Users users) {
        return this.usersService.list(users);
    }

    /**
     * 分页查询用户
     */
    @ApiOperation("分页显示用户")
    @GetMapping("/other/users/page")
    public ResultT<PageInfo<Users>> page(Users users, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum,@RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.usersService.page(users,pageNum,pageSize);
    }

    /**
     * 新增用户
     */
    @ApiOperation("新增用户")
    @PostMapping("/other/users/add")
    public ResultT<Void> add(@RequestBody Users users) {
        return this.usersService.add(users);
    }
    /**
     * 更新用户
     */
    @ApiOperation("更新用户")
    @PostMapping("/other/users/update")
    public ResultT<Void> update(@RequestBody Users users) {
        return this.usersService.update(users);
    }

    /**
     * 删除用户
     */
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "idList", value = "用户idList", dataTypeClass = String.class)
    @GetMapping("/other/users/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.usersService.del(idList);
    }

}
