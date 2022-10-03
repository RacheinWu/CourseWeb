package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Policy;
import com.he.ssm.service.other.PolicyService;
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
 * 政策Controller
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Api(tags = "政策接口")
@RestController
public class PolicyController{

    @Resource
    private PolicyService policyService;

    /**
     * 查询所有政策
     */
    @ApiOperation("查询所有政策")
    @GetMapping("/other/policy/list")
    public ResultT<List<Policy>> list(Policy policy) {
        return this.policyService.list(policy);
    }

    /**
     * 分页查询政策
     */
    @ApiOperation("分页显示政策")
    @GetMapping("/other/policy/page")
    public ResultT<PageInfo<Policy>> page(Policy policy, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum,@RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.policyService.page(policy,pageNum,pageSize);
    }

    /**
     * 新增政策
     */
    @ApiOperation("新增政策")
    @PostMapping("/other/policy/add")
    public ResultT<Void> add(@RequestBody Policy policy) {
        return this.policyService.add(policy);
    }
    /**
     * 更新政策
     */
    @ApiOperation("更新政策")
    @PostMapping("/other/policy/update")
    public ResultT<Void> update(@RequestBody Policy policy) {
        return this.policyService.update(policy);
    }

    /**
     * 删除政策
     */
    @ApiOperation("删除政策")
    @ApiImplicitParam(name = "idList", value = "政策idList", dataTypeClass = String.class)
    @GetMapping("/other/policy/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.policyService.del(idList);
    }
    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/policy/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.policyService.updateState(id, state);
    }

}
