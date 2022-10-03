package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.CoursewareReqBean;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Courseware;
import com.he.ssm.service.other.CoursewareService;
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
 * 课件(其他附件在附件表中)Controller
 * @author itaem
 * @date 2021-03-08 23:47:15
 */
@Api(tags = "课件(其他附件在附件表中)接口")
@RestController
public class CoursewareController{

    @Resource
    private CoursewareService coursewareService;

    /**
     * 查询所有课件(其他附件在附件表中)
     */
    @ApiOperation("查询所有课件(其他附件在附件表中)")
    @GetMapping("/other/courseware/list")
    public ResultT<List<Courseware>> list(Courseware courseware) {
        return this.coursewareService.list(courseware);
    }

    /**
     * 分页查询课件(其他附件在附件表中)
     */
    @ApiOperation("分页显示课件(其他附件在附件表中)")
    @GetMapping("/other/courseware/page")
    public ResultT<PageInfo<CoursewareReqBean>> page(Courseware courseware, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum, @RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.coursewareService.page(courseware,pageNum,pageSize);
    }

    /**
     * 新增课件(其他附件在附件表中)
     */
    @ApiOperation("新增课件(其他附件在附件表中)")
    @PostMapping("/other/courseware/add")
    public ResultT<Void> add(@RequestBody CoursewareReqBean courseware) {
        return this.coursewareService.add(courseware);
    }
    /**
     * 更新课件(其他附件在附件表中)
     */
    @ApiOperation("更新课件(其他附件在附件表中)")
    @PostMapping("/other/courseware/update")
    public ResultT<Void> update(@RequestBody CoursewareReqBean courseware) {
        return this.coursewareService.update(courseware);
    }

    /**
     * 删除课件(其他附件在附件表中)
     */
    @ApiOperation("删除课件(其他附件在附件表中)")
    @ApiImplicitParam(name = "idList", value = "课件(其他附件在附件表中)idList", dataTypeClass = String.class)
    @GetMapping("/other/courseware/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.coursewareService.del(idList);
    }
    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/courseware/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.coursewareService.updateState(id, state);
    }

}
