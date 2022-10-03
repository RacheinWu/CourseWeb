package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Teacher;
import com.he.ssm.service.other.TeacherService;
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
 * 教师Controller
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Api(tags = "教师接口")
@RestController
public class TeacherController{

    @Resource
    private TeacherService teacherService;

    /**
     * 查询所有教师
     */
    @ApiOperation("查询所有教师")
    @GetMapping("/other/teacher/list")
    public ResultT<List<Teacher>> list(Teacher teacher) {
        return this.teacherService.list(teacher);
    }

    /**
     * 分页查询教师
     */
    @ApiOperation("分页显示教师")
    @GetMapping("/other/teacher/page")
    public ResultT<PageInfo<Teacher>> page(Teacher teacher, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum,@RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.teacherService.page(teacher,pageNum,pageSize);
    }

    /**
     * 新增教师
     */
    @ApiOperation("新增教师")
    @PostMapping("/other/teacher/add")
    public ResultT<Void> add(@RequestBody Teacher teacher) {
        return this.teacherService.add(teacher);
    }
    /**
     * 更新教师
     */
    @ApiOperation("更新教师")
    @PostMapping("/other/teacher/update")
    public ResultT<Void> update(@RequestBody Teacher teacher) {
        return this.teacherService.update(teacher);
    }

    /**
     * 删除教师
     */
    @ApiOperation("删除教师")
    @ApiImplicitParam(name = "idList", value = "教师idList", dataTypeClass = String.class)
    @GetMapping("/other/teacher/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.teacherService.del(idList);
    }
    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/teacher/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.teacherService.updateState(id, state);
    }
}
