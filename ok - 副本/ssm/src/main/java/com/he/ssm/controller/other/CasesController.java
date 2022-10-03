package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Cases;
import com.he.ssm.service.other.CasesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
 * 案例Controller
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Api(tags = "案例接口")
@RestController
public class CasesController{

    @Resource
    private CasesService casesService;

    /**
     * 查询所有案例
     */
    @ApiOperation("查询所有案例")
    @GetMapping("/other/cases/list")
    public ResultT<List<Cases>> list(Cases cases) {
        return this.casesService.list(cases);
    }

    /**
     * 分页查询案例
     */
    @ApiOperation("分页显示案例")
    @GetMapping("/other/cases/page")
    public ResultT<PageInfo<Cases>> page(Cases cases, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum,@RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.casesService.page(cases,pageNum,pageSize);
    }

    /**
     * 新增案例
     */
    @ApiOperation("新增案例")
    @PostMapping("/other/cases/add")
    public ResultT<Void> add(@RequestBody Cases cases) {
        return this.casesService.add(cases);
    }
    /**
     * 更新案例
     */
    @ApiOperation("更新案例")
    @PostMapping("/other/cases/update")
    public ResultT<Void> update(@RequestBody Cases cases) {
        return this.casesService.update(cases);
    }

    /**
     * 删除案例
     */
    @ApiOperation("删除案例")
    @ApiImplicitParam(name = "idList", value = "案例idList", dataTypeClass = String.class)
    @GetMapping("/other/cases/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.casesService.del(idList);
    }
    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/cases/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.casesService.updateState(id, state);
    }

}
