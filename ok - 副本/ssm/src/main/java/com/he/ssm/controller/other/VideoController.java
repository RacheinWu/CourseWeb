package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.bean.VideoBean;
import com.he.ssm.entity.other.Video;
import com.he.ssm.service.other.VideoService;
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
 * 视频(其他附件)Controller
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Api(tags = "视频(其他附件)接口")
@RestController
public class VideoController{

    @Resource
    private VideoService videoService;

    /**
     * 查询所有视频(其他附件)
     */
    @ApiOperation("查询所有视频(其他附件)")
    @GetMapping("/other/video/list")
    public ResultT<List<Video>> list(Video video) {
        return this.videoService.list(video);
    }

    /**
     * 分页查询视频(其他附件)
     */
    @ApiOperation("分页显示视频(其他附件)")
    @GetMapping("/other/video/page")
    public ResultT<PageInfo<VideoBean>> page(Video video, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum, @RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.videoService.page(video,pageNum,pageSize);
    }

    /**
     * 新增视频(其他附件)
     */
    @ApiOperation("新增视频(其他附件)")
    @PostMapping("/other/video/add")
    public ResultT<Void> add(@RequestBody VideoBean video) {
        return this.videoService.add(video);
    }
    /**
     * 更新视频(其他附件)
     */
    @ApiOperation("更新视频(其他附件)")
    @PostMapping("/other/video/update")
    public ResultT<Void> update(@RequestBody VideoBean video) {
        return this.videoService.update(video);
    }

    /**
     * 删除视频(其他附件)
     */
    @ApiOperation("删除视频(其他附件)")
    @ApiImplicitParam(name = "idList", value = "视频(其他附件)idList", dataTypeClass = String.class)
    @GetMapping("/other/video/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.videoService.del(idList);
    }
    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/video/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.videoService.updateState(id, state);
    }
}
