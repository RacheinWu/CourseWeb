package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.PolicyDao;
import com.he.ssm.entity.other.Policy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 政策Service业务层处理
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class PolicyService{
    @Resource
    private UploadConfig uploadConfig;
    @Resource
    private PolicyDao policyDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Policy>> list(Policy policy) {
        List<Policy> list = this.policyDao.findByAll(policy);
        return ResultT.successWithData(list);
    }
    /**
     * 分页显示政策
     */
    public ResultT<PageInfo<Policy>> page(Policy policy,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(Objects.nonNull(policy.getBeginPublishDate())){
            policy.setBeginPublishDate(DateUtil.beginOfDay( policy.getBeginPublishDate()));
        }
        if(Objects.nonNull(policy.getEndPublishDate())){
            policy.setEndPublishDate(DateUtil.endOfDay( policy.getEndPublishDate()));
        }
        List<Policy> list=this.policyDao.findByCondition(policy);
        for (Policy b : list) {
            b.setPhotoUrl(uploadConfig.getHttpPrefix() + b.getPhotoUrl());
        }
        PageInfo<Policy> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增政策
     */
    public ResultT<Void> add(Policy policy){
        //判断数据唯一性
        Policy params=new Policy();
        params.setTitle(policy.getTitle());
        List<Policy> uniqueData = this.policyDao.findByAll(params);
        if (!uniqueData.isEmpty()) {
            return ResultT.errorWithMsg("数据已存在");
        }
        if ("发布".equals(policy.getState())) {
            policy.setPublishDate(new Date());
        }
        policy.setCreateDate( new Date());
        int count = this.policyDao.insert(policy);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改政策
     */
    public ResultT<Void> update(Policy policy) {
        Long id = policy.getId();
        //判断数据唯一性
        Policy params=new Policy();
        params.setTitle(policy.getTitle());
        List<Policy> uniqueData = this.policyDao.findByAll(params);
        boolean check = uniqueData.size() > 1 || (uniqueData.size() == 1 && !uniqueData.get(0).getId().equals(id));
        if (check) {
            return ResultT.errorWithMsg("数据已存在");
        }
        String photoUrl = policy.getPhotoUrl();
        String httpPrefix = uploadConfig.getHttpPrefix();
        if (StringUtils.isNotBlank(photoUrl) && photoUrl.startsWith(httpPrefix)) {
            policy.setPhotoUrl(photoUrl.replace(httpPrefix, ""));
        }
        Date now = new Date();
        if ("未发布".equals(policy.getState())) {
            now = null;
        }
        policy.setPublishDate(now);
        Policy existData = this.policyDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(policy, existData);
        int count = this.policyDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
    * 删除政策
    */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.policyDao.deleteByPrimaryKeyIn(idList);
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
        int count = this.policyDao.updateStateById(state, now, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

}
