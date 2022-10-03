package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Banner;
import com.he.ssm.service.other.BannerService;
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
 * 轮播图Controller
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Api(tags = "轮播图接口")
@RestController
public class BannerController {

    @Resource
    private BannerService bannerService;

    /**
     * 查询所有轮播图
     */
    @ApiOperation("查询所有轮播图")
    @GetMapping("/other/banner/list")
    public ResultT<List<Banner>> list(Banner banner) {
        return this.bannerService.list(banner);
    }

    /**
     * 分页查询轮播图
     */
    @ApiOperation("分页显示轮播图")
    @GetMapping("/other/banner/page")
    public ResultT<PageInfo<Banner>> page(Banner banner, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return this.bannerService.page(banner, pageNum, pageSize);
    }

    /**
     * 新增轮播图
     */
    @ApiOperation("新增轮播图")
    @PostMapping("/other/banner/add")
    public ResultT<Void> add(@RequestBody Banner banner) {
        return this.bannerService.add(banner);
    }

    /**
     * 更新轮播图
     */
    @ApiOperation("更新轮播图")
    @PostMapping("/other/banner/update")
    public ResultT<Void> update(@RequestBody Banner banner) {
        return this.bannerService.update(banner);
    }

    /**
     * 删除轮播图
     */
    @ApiOperation("删除轮播图")
    @ApiImplicitParam(name = "idList", value = "轮播图idList", dataTypeClass = String.class)
    @GetMapping("/other/banner/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.bannerService.del(idList);
    }

    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "轮播图id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/banner/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.bannerService.updateState(id, state);
    }

}
