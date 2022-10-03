package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Practice;
import com.he.ssm.service.other.PracticeService;
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
 * 社会实践(附件放在附件表中)Controller
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Api(tags = "社会实践(附件放在附件表中)接口")
@RestController
public class PracticeController {

    @Resource
    private PracticeService practiceService;

    /**
     * 查询所有社会实践(附件放在附件表中)
     */
    @ApiOperation("查询所有社会实践(附件放在附件表中)")
    @GetMapping("/other/practice/list")
    public ResultT<List<Practice>> list(Practice practice) {
        return this.practiceService.list(practice);
    }

    /**
     * 分页查询社会实践(附件放在附件表中)
     */
    @ApiOperation("分页显示社会实践(附件放在附件表中)")
    @GetMapping("/other/practice/page")
    public ResultT<PageInfo<Practice>> page(Practice practice, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return this.practiceService.page(practice, pageNum, pageSize);
    }

    /**
     * 新增社会实践(附件放在附件表中)
     */
    @ApiOperation("新增社会实践(附件放在附件表中)")
    @PostMapping("/other/practice/add")
    public ResultT<Void> add(@RequestBody Practice practice) {
        return this.practiceService.add(practice);
    }

    /**
     * 更新社会实践(附件放在附件表中)
     */
    @ApiOperation("更新社会实践(附件放在附件表中)")
    @PostMapping("/other/practice/update")
    public ResultT<Void> update(@RequestBody Practice practice) {
        return this.practiceService.update(practice);
    }

    /**
     * 删除社会实践(附件放在附件表中)
     */
    @ApiOperation("删除社会实践(附件放在附件表中)")
    @ApiImplicitParam(name = "idList", value = "社会实践(附件放在附件表中)idList", dataTypeClass = String.class)
    @GetMapping("/other/practice/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.practiceService.del(idList);
    }

    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "轮播图id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/practice/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.practiceService.updateState(id, state);
    }

}
