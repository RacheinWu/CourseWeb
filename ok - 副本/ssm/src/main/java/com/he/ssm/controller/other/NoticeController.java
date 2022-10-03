package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Notice;
import com.he.ssm.service.other.NoticeService;
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
 * 公告Controller
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Api(tags = "公告接口")
@RestController
public class NoticeController{

    @Resource
    private NoticeService noticeService;

    /**
     * 查询所有公告
     */
    @ApiOperation("查询所有公告")
    @GetMapping("/other/notice/list")
    public ResultT<List<Notice>> list(Notice notice) {
        return this.noticeService.list(notice);
    }

    /**
     * 分页查询公告
     */
    @ApiOperation("分页显示公告")
    @GetMapping("/other/notice/page")
    public ResultT<PageInfo<Notice>> page(Notice notice, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum,@RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.noticeService.page(notice,pageNum,pageSize);
    }

    /**
     * 新增公告
     */
    @ApiOperation("新增公告")
    @PostMapping("/other/notice/add")
    public ResultT<Void> add(@RequestBody Notice notice) {
        return this.noticeService.add(notice);
    }
    /**
     * 更新公告
     */
    @ApiOperation("更新公告")
    @PostMapping("/other/notice/update")
    public ResultT<Void> update(@RequestBody Notice notice) {
        return this.noticeService.update(notice);
    }

    /**
     * 删除公告
     */
    @ApiOperation("删除公告")
    @ApiImplicitParam(name = "idList", value = "公告idList", dataTypeClass = String.class)
    @GetMapping("/other/notice/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.noticeService.del(idList);
    }
    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/notice/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.noticeService.updateState(id, state);
    }
}
