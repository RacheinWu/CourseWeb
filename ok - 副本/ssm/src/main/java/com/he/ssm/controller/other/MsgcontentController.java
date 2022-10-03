package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Msgcontent;
import com.he.ssm.service.other.MsgcontentService;
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
 * 留言内容Controller
 *
 * @author itaem
 * @date 2021-03-21 14:52:17
 */
@Api(tags = "留言内容接口")
@RestController
public class MsgcontentController {

    @Resource
    private MsgcontentService msgcontentService;

    /**
     * 查询所有留言内容
     */
    @ApiOperation("查询所有留言内容")
    @GetMapping("/other/msgcontent/list")
    public ResultT<List<Msgcontent>> list(Msgcontent msgcontent) {
        return this.msgcontentService.list(msgcontent);
    }

    /**
     * 分页查询留言内容
     */
    @ApiOperation("分页显示留言内容")
    @GetMapping("/other/msgcontent/page")
    public ResultT<PageInfo<Msgcontent>> page(Msgcontent msgcontent, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return this.msgcontentService.page(msgcontent, pageNum, pageSize);
    }

    /**
     * 新增留言内容
     */
    @ApiOperation("新增留言内容")
    @PostMapping("/other/msgcontent/add")
    public ResultT<Void> add(@RequestBody Msgcontent msgcontent) {
        return this.msgcontentService.add(msgcontent);
    }

    /**
     * 更新留言内容
     */
    @ApiOperation("更新留言内容")
    @PostMapping("/other/msgcontent/update")
    public ResultT<Void> update(@RequestBody Msgcontent msgcontent) {
        return this.msgcontentService.update(msgcontent);
    }

    /**
     * 删除留言内容
     */
    @ApiOperation("删除留言内容")
    @ApiImplicitParam(name = "idList", value = "留言内容idList", dataTypeClass = String.class)
    @GetMapping("/other/msgcontent/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.msgcontentService.del(idList);
    }

    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/msgcontent/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.msgcontentService.updateState(id, state);
    }
}
