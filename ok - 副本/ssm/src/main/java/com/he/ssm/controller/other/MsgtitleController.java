package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Msgtitle;
import com.he.ssm.service.other.MsgtitleService;
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
 * 留言标题Controller
 * @author itaem
 * @date 2021-03-21 14:52:17
 */
@Api(tags = "留言标题接口")
@RestController
public class MsgtitleController{

    @Resource
    private MsgtitleService msgtitleService;

    /**
     * 查询所有留言标题
     */
    @ApiOperation("查询所有留言标题")
    @GetMapping("/other/msgtitle/list")
    public ResultT<List<Msgtitle>> list(Msgtitle msgtitle) {
        return this.msgtitleService.list(msgtitle);
    }

    /**
     * 分页查询留言标题
     */
    @ApiOperation("分页显示留言标题")
    @GetMapping("/other/msgtitle/page")
    public ResultT<PageInfo<Msgtitle>> page(Msgtitle msgtitle, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum,@RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.msgtitleService.page(msgtitle,pageNum,pageSize);
    }

    /**
     * 新增留言标题
     */
    @ApiOperation("新增留言标题")
    @PostMapping("/other/msgtitle/add")
    public ResultT<Void> add(@RequestBody Msgtitle msgtitle) {
        return this.msgtitleService.add(msgtitle);
    }
    /**
     * 更新留言标题
     */
    @ApiOperation("更新留言标题")
    @PostMapping("/other/msgtitle/update")
    public ResultT<Void> update(@RequestBody Msgtitle msgtitle) {
        return this.msgtitleService.update(msgtitle);
    }

    /**
     * 删除留言标题
     */
    @ApiOperation("删除留言标题")
    @ApiImplicitParam(name = "idList", value = "留言标题idList", dataTypeClass = String.class)
    @GetMapping("/other/msgtitle/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.msgtitleService.del(idList);
    }
    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/msgtitle/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.msgtitleService.updateState(id, state);
    }

}
