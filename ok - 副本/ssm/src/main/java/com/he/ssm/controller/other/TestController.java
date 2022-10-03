package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Test;
import com.he.ssm.service.other.TestService;
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
 * 测试代码生成Controller
 * @author itaem
 * @date 2021-03-18 11:18:17
 */
@Api(tags = "测试代码生成接口")
@RestController
public class TestController{

    @Resource
    private TestService testService;

    /**
     * 查询所有测试代码生成
     */
    @ApiOperation("查询所有测试代码生成")
    @GetMapping("/other/test/list")
    public ResultT<List<Test>> list(Test test) {
        return this.testService.list(test);
    }

    /**
     * 分页查询测试代码生成
     */
    @ApiOperation("分页显示测试代码生成")
    @GetMapping("/other/test/page")
    public ResultT<PageInfo<Test>> page(Test test, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum,@RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.testService.page(test,pageNum,pageSize);
    }

    /**
     * 新增测试代码生成
     */
    @ApiOperation("新增测试代码生成")
    @PostMapping("/other/test/add")
    public ResultT<Void> add(@RequestBody Test test) {
        return this.testService.add(test);
    }
    /**
     * 更新测试代码生成
     */
    @ApiOperation("更新测试代码生成")
    @PostMapping("/other/test/update")
    public ResultT<Void> update(@RequestBody Test test) {
        return this.testService.update(test);
    }

    /**
     * 删除测试代码生成
     */
    @ApiOperation("删除测试代码生成")
    @ApiImplicitParam(name = "idList", value = "测试代码生成idList", dataTypeClass = String.class)
    @GetMapping("/other/test/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.testService.del(idList);
    }

}
