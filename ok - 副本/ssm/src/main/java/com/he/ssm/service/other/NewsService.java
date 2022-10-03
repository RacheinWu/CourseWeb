package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.NewsDao;
import com.he.ssm.entity.other.News;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 新闻Service业务层处理
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class NewsService{
    @Resource
    private UploadConfig uploadConfig;
    @Resource
    private NewsDao      newsDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<News>> list(News news) {
        List<News> list = this.newsDao.findByAll(news);
        return ResultT.successWithData(list);
    }
    /**
     * 分页显示新闻
     */
    public ResultT<PageInfo<News>> page(News news,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(Objects.nonNull(news.getBeginPublishDate())){
            news.setBeginPublishDate(DateUtil.beginOfDay( news.getBeginPublishDate()));
        }
        if(Objects.nonNull(news.getEndPublishDate())){
            news.setEndPublishDate(DateUtil.endOfDay( news.getEndPublishDate()));
        }
        List<News> list=this.newsDao.findByCondition(news);
        for (News b : list) {
            b.setPhotoUrl(uploadConfig.getHttpPrefix() + b.getPhotoUrl());
        }
        PageInfo<News> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增新闻
     */
    public ResultT<Void> add(News news){
        //判断数据唯一性
        News params=new News();
        params.setTitle(news.getTitle());
        List<News> uniqueData = this.newsDao.findByAll(params);
        if (!uniqueData.isEmpty()) {
            return ResultT.errorWithMsg("数据已存在");
        }
        String photoUrl = news.getPhotoUrl();
        String httpPrefix = uploadConfig.getHttpPrefix();
        if (StringUtils.isNotBlank(photoUrl) && photoUrl.startsWith(httpPrefix)) {
            news.setPhotoUrl(photoUrl.replace(httpPrefix, ""));
        }
        if ("发布".equals(news.getState())) {
            news.setPublishDate(new Date());
        }
        news.setCreateDate( new Date());
        int count = this.newsDao.insert(news);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改新闻
     */
    public ResultT<Void> update(News news) {
        Long id = news.getId();
        //判断数据唯一性
        News params=new News();
        params.setTitle(news.getTitle());
        List<News> uniqueData = this.newsDao.findByAll(params);
        boolean check = uniqueData.size() > 1 || (uniqueData.size() == 1 && !uniqueData.get(0).getId().equals(id));
        if (check) {
            return ResultT.errorWithMsg("数据已存在");
        }
        String photoUrl = news.getPhotoUrl();
        String httpPrefix = uploadConfig.getHttpPrefix();
        if (StringUtils.isNotBlank(photoUrl) && photoUrl.startsWith(httpPrefix)) {
            news.setPhotoUrl(photoUrl.replace(httpPrefix, ""));
        }
        Date now = new Date();
        if ("未发布".equals(news.getState())) {
            now = null;
        }
        news.setPublishDate(now);
        News existData = this.newsDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(news, existData);
        int count = this.newsDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
    * 删除新闻
    */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.newsDao.deleteByPrimaryKeyIn(idList);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }
    public ResultT<Void> updateState(Long id, String state) {
        Date now = new Date();
        if ("未发布".equals(state)) {
            now = null;
        }
        int count = this.newsDao.updateStateById(state, now, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

}
