package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Experience;
import com.he.ssm.service.other.ExperienceService;
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
 * 经验Controller
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Api(tags = "经验接口")
@RestController
public class ExperienceController{

    @Resource
    private ExperienceService experienceService;

    /**
     * 查询所有经验
     */
    @ApiOperation("查询所有经验")
    @GetMapping("/other/experience/list")
    public ResultT<List<Experience>> list(Experience experience) {
        return this.experienceService.list(experience);
    }

    /**
     * 分页查询经验
     */
    @ApiOperation("分页显示经验")
    @GetMapping("/other/experience/page")
    public ResultT<PageInfo<Experience>> page(Experience experience, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum,@RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.experienceService.page(experience,pageNum,pageSize);
    }

    /**
     * 新增经验
     */
    @ApiOperation("新增经验")
    @PostMapping("/other/experience/add")
    public ResultT<Void> add(@RequestBody Experience experience) {
        return this.experienceService.add(experience);
    }
    /**
     * 更新经验
     */
    @ApiOperation("更新经验")
    @PostMapping("/other/experience/update")
    public ResultT<Void> update(@RequestBody Experience experience) {
        return this.experienceService.update(experience);
    }

    /**
     * 删除经验
     */
    @ApiOperation("删除经验")
    @ApiImplicitParam(name = "idList", value = "经验idList", dataTypeClass = String.class)
    @GetMapping("/other/experience/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.experienceService.del(idList);
    }
    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/experience/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.experienceService.updateState(id, state);
    }

}
