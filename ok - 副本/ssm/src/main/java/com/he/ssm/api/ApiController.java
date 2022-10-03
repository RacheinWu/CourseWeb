package com.he.ssm.api;

import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.MsgTitleBean;
import com.he.ssm.bean.ResultT;
import com.he.ssm.bean.api.IndexResDto;
import com.he.ssm.entity.other.Cases;
import com.he.ssm.entity.other.Courseware;
import com.he.ssm.entity.other.Experience;
import com.he.ssm.entity.other.Msgcontent;
import com.he.ssm.entity.other.News;
import com.he.ssm.entity.other.Notice;
import com.he.ssm.entity.other.Policy;
import com.he.ssm.entity.other.Practice;
import com.he.ssm.entity.other.Teacher;
import com.he.ssm.entity.other.Video;
import com.he.ssm.entity.other.Intro;
import com.he.ssm.service.api.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author itaem
 * date:2020-08-26 2020/8/26:9:48
 */
@Slf4j
@Api(tags = "所有接口")
@RestController
public class ApiController {
    @Resource
    private ApiService apiService;


    @ApiOperation("首页")
    @GetMapping("/api/index")
    public ResultT<IndexResDto> index() {
        return this.apiService.index();
    }

    @ApiOperation("首页-公告分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "标题", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    @GetMapping("/api/index/notice/page")
    public ResultT<PageInfo<Notice>> noticePage(String search, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return this.apiService.noticePage(search, pageNum, pageSize);
    }


    @ApiOperation("首页-新闻分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "标题、来源", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    @GetMapping("/api/index/news/page")
    public ResultT<PageInfo<News>> newsPage(String search, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return this.apiService.newsPage(search, pageNum, pageSize);
    }


    @ApiOperation("首页-政策分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "标题、来源", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    @GetMapping("/api/index/policy/page")
    public ResultT<PageInfo<Policy>> policyPage(String search, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return this.apiService.policyPage(search, pageNum, pageSize);
    }


    @ApiOperation("社会实践分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "实践名称、队伍名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    @GetMapping("/api/index/practice/page")
    public ResultT<PageInfo<Practice>> practicePage(String search, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return this.apiService.practicePage(search, pageNum, pageSize);
    }


    @ApiOperation("案例解读分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "标题、作者", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    @GetMapping("/api/index/cases/page")
    public ResultT<PageInfo<Cases>> casesPage(String search, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return this.apiService.casesPage(search, pageNum, pageSize);
    }


    @ApiOperation("经验借鉴分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "标题、作者", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    @GetMapping("/api/index/experience/page")
    public ResultT<PageInfo<Experience>> experiencePage(String search, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return this.apiService.experiencePage(search, pageNum, pageSize);
    }


    @ApiOperation("教学名师分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "老师名称", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    @GetMapping("/api/index/teacher/page")
    public ResultT<PageInfo<Teacher>> teacherPage(String search, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return this.apiService.teacherPage(search, pageNum, pageSize);
    }


    @ApiOperation("教学视频分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "标题、作者", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    @GetMapping("/api/index/video/page")
    public ResultT<PageInfo<Video>> videoPage(String search, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return this.apiService.videoPage(search, pageNum, pageSize);
    }


    @ApiOperation("教学资料分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "标题、作者", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "pageSize", value = "页大小", dataTypeClass = Integer.class)
    })
    @GetMapping("/api/index/courseware/page")
    public ResultT<PageInfo<Courseware>> coursewarePage(String search, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return this.apiService.coursewarePage(search, pageNum, pageSize);
    }

    @ApiOperation("获取单条公告")
    @GetMapping("/api/index/notice/get")
    public ResultT<Notice> noticeGet(Long id) {
        return this.apiService.noticeGet(id);
    }

    @ApiOperation("获取单条新闻")
    @GetMapping("/api/index/news/get")
    public ResultT<News> newsGet(Long id) {
        return this.apiService.newsGet(id);
    }

    @ApiOperation("获取单条政策")
    @GetMapping("/api/index/policy/get")
    public ResultT<Policy> policyGet(Long id) {
        return this.apiService.policyGet(id);
    }

    @ApiOperation("获取单条社会实践")
    @GetMapping("/api/index/practice/get")
    public ResultT<Practice> practiceGet(Long id) {
        return this.apiService.practiceGet(id);
    }

    @ApiOperation("获取单条社会实践")
    @GetMapping("/api/index/cases/get")
    public ResultT<Cases> casesGet(Long id) {
        return this.apiService.casesGet(id);
    }

    @ApiOperation("获取单条经验借鉴")
    @GetMapping("/api/index/experience/get")
    public ResultT<Experience> experienceGet(Long id) {
        return this.apiService.experienceGet(id);
    }

    @ApiOperation("获取单条教学名师")
    @GetMapping("/api/index/teacher/get")
    public ResultT<Teacher> teacherGet(Long id) {
        return this.apiService.teacherGet(id);
    }

    @ApiOperation("获取单条教学视频")
    @GetMapping("/api/index/video/get")
    public ResultT<Video> videoGet(Long id) {
        return this.apiService.videoGet(id);
    }

    @ApiOperation("获取单条教学资料")
    @GetMapping("/api/index/courseware/get")
    public ResultT<Courseware> coursewareGet(Long id) {
        return this.apiService.coursewareGet(id);
    }

    @ApiOperation("获取留言")
    @GetMapping("/api/index/msg/find")
    public ResultT<List<MsgTitleBean>> msgFind() {
        return this.apiService.msgFind();
    }

    @ApiOperation("获取留言")
    @PostMapping("/api/index/msg/add")
    public ResultT<Void> msgAdd(@RequestBody  Msgcontent msgcontent) {
        return this.apiService.msgAdd(msgcontent);
    }

    @ApiOperation("学院课程介绍")
    @PostMapping("/api/index/intro/update")
    public ResultT<Void> introAdd(@RequestBody  Intro intro) {
        return this.apiService.introUpdate(intro);
    }
    @ApiOperation("学院课程介绍")
    @PostMapping("/api/index/intro/get")
    public ResultT<Intro> introGet(@RequestBody  Intro intro) {
        return this.apiService.introGet(intro);
    }


}