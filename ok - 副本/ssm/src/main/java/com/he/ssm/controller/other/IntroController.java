package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.Intro;
import com.he.ssm.service.other.IntroService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

public class IntroController {
    @Resource
    private IntroService introService;

    /**
     * 查询新闻
     */
    @ApiOperation("查询介绍")
    @GetMapping("/other/intro/get")
    public ResultT<Intro> get(Intro intro) {
        return this.introService.get(intro);
    }
    /**
     * 更新新闻
     */
    @ApiOperation("更新介绍")
    @PostMapping("/other/intro/update")
    public ResultT<Void> update(@RequestBody Intro intro) {
        return this.introService.update(intro);
    }




}
