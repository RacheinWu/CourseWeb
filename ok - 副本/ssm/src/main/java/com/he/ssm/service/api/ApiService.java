package com.he.ssm.service.api;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.FileInfo;
import com.he.ssm.bean.MsgTitleBean;
import com.he.ssm.bean.ResultT;
import com.he.ssm.bean.api.CoursewareResDto;
import com.he.ssm.bean.api.IndexResDto;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.*;
import com.he.ssm.entity.other.Attach;
import com.he.ssm.entity.other.Banner;
import com.he.ssm.entity.other.Cases;
import com.he.ssm.entity.other.Courseware;
import com.he.ssm.entity.other.Experience;
import com.he.ssm.entity.other.Msgcontent;
import com.he.ssm.entity.other.Msgtitle;
import com.he.ssm.entity.other.News;
import com.he.ssm.entity.other.Notice;
import com.he.ssm.entity.other.Policy;
import com.he.ssm.entity.other.Practice;
import com.he.ssm.entity.other.Teacher;
import com.he.ssm.entity.other.Video;
import com.he.ssm.entity.other.Intro;
import com.he.ssm.redis.RedisService;
import com.he.ssm.redis.myPrefixKey.CountKey;
import com.he.ssm.service.other.AttachService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 用户Service业务层处理
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class ApiService {
    @Resource
    private UploadConfig  uploadConfig;
    @Resource
    private BannerDao     bannerDao;
    @Resource
    private NoticeDao     noticeDao;
    @Resource
    private NewsDao       newsDao;
    @Resource
    private PolicyDao     policyDao;
    @Resource
    private PracticeDao   practiceDao;
    @Resource
    private CasesDao      casesDao;
    @Resource
    private ExperienceDao experienceDao;
    @Resource
    private TeacherDao    teacherDao;
    @Resource
    private VideoDao      videoDao;
    @Resource
    private CoursewareDao coursewareDao;
    @Resource
    private AttachDao     attachDao;
    @Resource
    private MsgtitleDao   msgtitleDao;
    @Resource
    private MsgcontentDao msgcontentDao;
    @Resource
    private IntroDao introDao;
    @Autowired
    private AttachService attachService;
    @Autowired
    private RedisService redisService;

    public ResultT<IndexResDto> index() {
        String publishState = "发布";
        List<Banner> bannerList = this.bannerDao.findByStateOrderByOrderNumDesc(publishState);
        for (Banner banner : bannerList) {
            banner.setPhotoUrl(uploadConfig.getHttpPrefix() + banner.getPhotoUrl());
        }
        List<Notice> noticeList = this.noticeDao.findByStateOrderByPublishDateDesc(null, publishState);
        List<News> newsList = this.newsDao.findByStateOrderByPublishDateDesc(null, publishState);
        for (News news : newsList) {
            news.setPhotoUrl(uploadConfig.getHttpPrefix() + news.getPhotoUrl());
        }
        List<Policy> policyList = this.policyDao.findByStateOrderByPublishDateDesc(null, publishState);
        for (Policy policy : policyList) {
            policy.setPhotoUrl(uploadConfig.getHttpPrefix() + policy.getPhotoUrl());
        }
        List<Teacher> teacherList = this.teacherDao.findByStateOrderByPublishDateDesc(null, publishState,"是");
        for (Teacher teacher : teacherList) {
            teacher.setPhotoUrl(uploadConfig.getHttpPrefix() + teacher.getPhotoUrl());
        }
        return ResultT.successWithData(new IndexResDto(bannerList, noticeList, newsList, policyList,teacherList));

    }

    public ResultT<PageInfo<Notice>> noticePage(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String publishState = "发布";
        List<Notice> list = this.noticeDao.findByStateOrderByPublishDateDesc(search, publishState);
        PageInfo<Notice> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    public ResultT<PageInfo<News>> newsPage(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String publishState = "发布";
        List<News> list = this.newsDao.findByStateOrderByPublishDateDesc(search, publishState);
        for (News news : list) {
            news.setPhotoUrl(uploadConfig.getHttpPrefix() + news.getPhotoUrl());
        }
        PageInfo<News> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    public ResultT<PageInfo<Policy>> policyPage(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String publishState = "发布";
        List<Policy> list = this.policyDao.findByStateOrderByPublishDateDesc(search, publishState);
        for (Policy policy : list) {
            policy.setPhotoUrl(uploadConfig.getHttpPrefix() + policy.getPhotoUrl());
        }
        PageInfo<Policy> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    public ResultT<PageInfo<Practice>> practicePage(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String publishState = "发布";
        List<Practice> list = this.practiceDao.findByStateOrderByPublishDateDesc(search, publishState);
        for (Practice practice : list) {
            practice.setPhotoUrl(uploadConfig.getHttpPrefix() + practice.getPhotoUrl());
        }
        PageInfo<Practice> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    public ResultT<PageInfo<Cases>> casesPage(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String publishState = "发布";
        List<Cases> list = this.casesDao.findByStateOrderByPublishDateDesc(search, publishState);
        PageInfo<Cases> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    public ResultT<PageInfo<Experience>> experiencePage(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String publishState = "发布";
        List<Experience> list = this.experienceDao.findByStateOrderByPublishDateDesc(search, publishState);
        for (Experience experience : list) {
            experience.setPhotoUrl(uploadConfig.getHttpPrefix() + experience.getPhotoUrl());
        }
        PageInfo<Experience> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    public ResultT<PageInfo<Teacher>> teacherPage(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String publishState = "发布";
        List<Teacher> list = this.teacherDao.findByStateOrderByPublishDateDesc(search, publishState,null);
        for (Teacher teacher : list) {
            teacher.setPhotoUrl(uploadConfig.getHttpPrefix() + teacher.getPhotoUrl());
        }
        PageInfo<Teacher> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    public ResultT<PageInfo<Video>> videoPage(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String publishState = "发布";
        List<Video> list = this.videoDao.findByStateOrderByPublishDateDesc(search, publishState);
        for (Video video : list) {
            video.setPhotoUrl(uploadConfig.getHttpPrefix() + video.getPhotoUrl());
            video.setVideoUrl(uploadConfig.getHttpPrefix() + video.getVideoUrl());
        }
        PageInfo<Video> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    public ResultT<PageInfo<Courseware>> coursewarePage(String search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        String publishState = "发布";
        List<Courseware> list = this.coursewareDao.findByStateOrderByPublishDateDesc(search, publishState);
        for (Courseware courseware : list) {
            courseware.setCoursewareUrl(uploadConfig.getHttpPrefix() + courseware.getCoursewareUrl());
        }
        PageInfo<Courseware> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    public ResultT<Notice> noticeGet(Long id) {
        Notice notice = this.noticeDao.selectByPrimaryKey(id);
        return ResultT.successWithData(notice);
    }

    public ResultT<News> newsGet(Long id) {
        News news = this.newsDao.selectByPrimaryKey(id);
        news.setPhotoUrl(uploadConfig.getHttpPrefix() + news.getPhotoUrl());
        return ResultT.successWithData(news);
    }

    public ResultT<Policy> policyGet(Long id) {
        Policy policy = this.policyDao.selectByPrimaryKey(id);
        policy.setPhotoUrl(uploadConfig.getHttpPrefix() + policy.getPhotoUrl());
        return ResultT.successWithData(policy);
    }

    public ResultT<Practice> practiceGet(Long id) {
        Practice practice = this.practiceDao.selectByPrimaryKey(id);
        practice.setPhotoUrl(uploadConfig.getHttpPrefix() + practice.getPhotoUrl());
        return ResultT.successWithData(practice);
    }

    public ResultT<Cases> casesGet(Long id) {
        Cases cases = this.casesDao.selectByPrimaryKey(id);
        //关联上一条和下一条信息
        Date publishDate = cases.getPublishDate();
        Cases last = this.casesDao.getByPublishDateLessThanOrderByPublishDateDesc(publishDate);
        if (Objects.nonNull(last)) {
            cases.setLastId(last.getId());
            cases.setLastTitle(last.getTitile());
        }
        Cases next = this.casesDao.getByPublishDateGreaterThanOrderByPublishDate(publishDate);
        if (Objects.nonNull(next)) {
            cases.setNextId(next.getId());
            cases.setNextTitle(next.getTitile());
        }
        return ResultT.successWithData(cases);
    }

    public ResultT<Experience> experienceGet(Long id) {
        Experience experience = this.experienceDao.selectByPrimaryKey(id);
        //关联上一条和下一条信息
        Date publishDate = experience.getPublishDate();
        Experience last = this.experienceDao.getByPublishDateLessThanOrderByPublishDateDesc(publishDate);
        if (Objects.nonNull(last)) {
            experience.setLastId(last.getId());
            experience.setLastTitle(last.getTitle());
        }
        Experience next = this.experienceDao.getByPublishDateGreaterThanOrderByPublishDate(publishDate);
        if (Objects.nonNull(next)) {
            experience.setNextId(next.getId());
            experience.setNextTitle(next.getTitle());
        }
        return ResultT.successWithData(experience);
    }

    public ResultT<Teacher> teacherGet(Long id) {
        Teacher teacher = this.teacherDao.selectByPrimaryKey(id);
        teacher.setPhotoUrl(uploadConfig.getHttpPrefix() + teacher.getPhotoUrl());
        return ResultT.successWithData(teacher);
    }

    public ResultT<Video> videoGet(Long id) {
        Video video = this.videoDao.selectByPrimaryKey(id);
        video.setPhotoUrl(uploadConfig.getHttpPrefix()+video.getPhotoUrl());
        video.setVideoUrl(uploadConfig.getHttpPrefix()+video.getVideoUrl());
        //关联上一条和下一条信息
        Date publishDate = video.getPublishDate();
        Video last = this.videoDao.getByPublishDateLessThanOrderByPublishDateDesc(publishDate);
        if (Objects.nonNull(last)) {
            video.setLastId(last.getId());
            video.setLastTitle(last.getTitle());
        }
        Video next = this.videoDao.getByPublishDateGreaterThanOrderByPublishDate(publishDate);
        if (Objects.nonNull(next)) {
            video.setNextId(next.getId());
            video.setNextTitle(next.getTitle());
        }
        return ResultT.successWithData(video);
    }

    public ResultT<Courseware> coursewareGet(Long id) {
        Courseware courseware = this.coursewareDao.selectByPrimaryKey(id);
        //关联上一条和下一条信息
        Date publishDate = courseware.getPublishDate();
        Courseware last = this.coursewareDao.getByPublishDateLessThanOrderByPublishDateDesc(publishDate);
        if (Objects.nonNull(last)) {
            courseware.setLastId(last.getId());
            courseware.setLastTitle(last.getTile());
        }
        Courseware next = this.coursewareDao.getByPublishDateGreaterThanOrderByPublishDate(publishDate);
        if (Objects.nonNull(next)) {
            courseware.setNextId(next.getId());
            courseware.setNextTitle(next.getTile());
        }
        CoursewareResDto resDto = BeanUtil.toBean(courseware, CoursewareResDto.class);
        List<FileInfo> fileInfoList = new ArrayList<>();
//        List<Attach> attachList = this.attachDao.findByDataIdAndAttachType(id, "courseware");
        //从redis中 同步数据到 mysql中：
        List<Attach> attachList = this.attachService.lambdaQuery()
                .eq(Attach::getId, id)
                .eq(Attach::getAttachType, "courseware")
                .list();
        for (Attach attach : attachList) {
            Integer count = redisService.get(CountKey.ATTACK_TOTAL, attach.getRelativePath(), Integer.class);
            //更新次数:
            attach.setDownloadCount(count);
            FileInfo fileInfo = BeanUtil.toBean(attach, FileInfo.class);
            fileInfo.setNetworkPath(this.uploadConfig.getHttpPrefix() + attach.getRelativePath());
            fileInfoList.add(fileInfo);
        }
        //批次更新下载次数
        attachService.updateBatchById(attachList);
        resDto.setFileInfoList(fileInfoList);
        return ResultT.successWithData(resDto);
    }

    public ResultT<Void> msgAdd(Msgcontent msgcontent) {
        msgcontent.setCreateDate(new Date());
        if (StringUtils.isBlank(msgcontent.getState())) {
            msgcontent.setState("未审核");
        }
        if (StringUtils.isBlank(msgcontent.getMsgType())) {
            msgcontent.setMsgType("留言");
        }
        int count = this.msgcontentDao.insert(msgcontent);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }
    public ResultT<List<MsgTitleBean>> msgFind() {
        List<MsgTitleBean> list = new ArrayList<>();
        List<Msgtitle> msgtitleList = this.msgtitleDao.findByState("发布");
        for (Msgtitle msgtitle : msgtitleList) {
            MsgTitleBean msgTitleBean = BeanUtil.toBean(msgtitle, MsgTitleBean.class);
            List<Msgcontent> msgcontentList = this.msgcontentDao.findByTitleIdAndState(msgTitleBean.getId(), "已审核");
            msgTitleBean.setMsgcontentList(msgcontentList);
            list.add(msgTitleBean);
        }
        return ResultT.successWithData(list);
    }




    public ResultT<Intro> introGet(Intro param) {
        Intro intro;
        intro = this.introDao.selectByPrimaryKey(param);
        return ResultT.successWithData(intro);
    }
    public ResultT<Void> introUpdate(Intro param) {
        int id = param.getId();
        Intro intro=new Intro();
        intro.setId(id);
        intro.setTitle(param.getTitle());
        intro.setAuthor(param.getAuthor());
        intro.setContent(param.getContent());
        Date now = new Date();
        intro.setCreateTime(now);
        this.introDao.updateByPrimaryKey(intro);
        return ResultT.success();
    }
}
