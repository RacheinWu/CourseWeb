package com.he.ssm.controller.other;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.entity.other.News;
import com.he.ssm.service.other.NewsService;
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
 * 新闻Controller
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Api(tags = "新闻接口")
@RestController
public class NewsController{

    @Resource
    private NewsService newsService;

    /**
     * 查询所有新闻
     */
    @ApiOperation("查询所有新闻")
    @GetMapping("/other/news/list")
    public ResultT<List<News>> list(News news) {
        return this.newsService.list(news);
    }

    /**
     * 分页查询新闻
     */
    @ApiOperation("分页显示新闻")
    @GetMapping("/other/news/page")
    public ResultT<PageInfo<News>> page(News news, @RequestParam(value = "pageNum",defaultValue ="1" )Integer pageNum,@RequestParam(value = "pageSize",defaultValue ="10" ) Integer pageSize) {
        return this.newsService.page(news,pageNum,pageSize);
    }

    /**
     * 新增新闻
     */
    @ApiOperation("新增新闻")
    @PostMapping("/other/news/add")
    public ResultT<Void> add(@RequestBody News news) {
        return this.newsService.add(news);
    }
    /**
     * 更新新闻
     */
    @ApiOperation("更新新闻")
    @PostMapping("/other/news/update")
    public ResultT<Void> update(@RequestBody News news) {
        return this.newsService.update(news);
    }

    /**
     * 删除新闻
     */
    @ApiOperation("删除新闻")
    @ApiImplicitParam(name = "idList", value = "新闻idList", dataTypeClass = String.class)
    @GetMapping("/other/news/del/{idList}")
    public ResultT<Void> del(@PathVariable List<Long> idList) {
        return this.newsService.del(idList);
    }
    /**
     * 更新状态
     */
    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "新闻id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "state", value = "状态", dataTypeClass = String.class)
    })
    @GetMapping("/other/news/updateState")
    public ResultT<Void> updateState(Long id, String state) {
        return this.newsService.updateState(id, state);
    }

}
