package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.BannerDao;
import com.he.ssm.entity.other.Banner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * 轮播图Service业务层处理
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class BannerService {
    @Resource
    private UploadConfig uploadConfig;
    @Resource
    private BannerDao    bannerDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Banner>> list(Banner banner) {
        List<Banner> list = this.bannerDao.findByAll(banner);
        return ResultT.successWithData(list);
    }

    /**
     * 分页显示轮播图
     */
    public ResultT<PageInfo<Banner>> page(Banner banner, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (Objects.nonNull(banner.getBeginPublishDate())) {
            banner.setBeginPublishDate(DateUtil.beginOfDay(banner.getBeginPublishDate()));
        }
        if (Objects.nonNull(banner.getEndPublishDate())) {
            banner.setEndPublishDate(DateUtil.endOfDay(banner.getEndPublishDate()));
        }
        List<Banner> list = this.bannerDao.findByCondition(banner);
        for (Banner b : list) {
            b.setPhotoUrl(uploadConfig.getHttpPrefix() + b.getPhotoUrl());
        }
        PageInfo<Banner> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增轮播图
     */
    public ResultT<Void> add(Banner banner) {
        //判断数据唯一性
        Banner params = new Banner();
        params.setTitle(banner.getTitle());
        List<Banner> uniqueData = this.bannerDao.findByAll(params);
        if (!uniqueData.isEmpty()) {
            return ResultT.errorWithMsg("数据已存在");
        }
        if ("发布".equals(banner.getState())) {
            banner.setPublishDate(new Date());
        }
        banner.setCreateDate(new Date());
        int count = this.bannerDao.insert(banner);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改轮播图
     */
    public ResultT<Void> update(Banner banner) {
        Long id = banner.getId();
        //判断数据唯一性
        Banner params = new Banner();
        params.setTitle(banner.getTitle());
        List<Banner> uniqueData = this.bannerDao.findByAll(params);
        boolean check = uniqueData.size() > 1 || (uniqueData.size() == 1 && !uniqueData.get(0).getId().equals(id));
        if (check) {
            return ResultT.errorWithMsg("数据已存在");
        }
        String photoUrl = banner.getPhotoUrl();
        String httpPrefix = uploadConfig.getHttpPrefix();
        if (StringUtils.isNotBlank(photoUrl) && photoUrl.startsWith(httpPrefix)) {
            banner.setPhotoUrl(photoUrl.replace(httpPrefix, ""));
        }
        Date now = new Date();
        if ("未发布".equals(banner.getState())) {
            now = null;
        }
        banner.setPublishDate(now);
        Banner existData = this.bannerDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(banner, existData);
        int count = this.bannerDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 删除轮播图
     */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.bannerDao.deleteByPrimaryKeyIn(idList);
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
        int count = this.bannerDao.updateStateById(state, now, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

}
