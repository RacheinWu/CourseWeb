package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.IntroDao;
import com.he.ssm.entity.other.Intro;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class IntroService {
    @Resource
    private UploadConfig uploadConfig;
    @Resource
    private IntroDao introDao;

    /**
     * 查询介绍
     */
    public ResultT<Intro> get(Intro intro){
        Intro resIntro = this.introDao.selectByPrimaryKey(intro);
        return ResultT.successWithData(resIntro);
    }
    /**
     * 修改介绍
     */
    public ResultT<Void> update(Intro param) {
        Long id = param.getId();
        Intro intro=new Intro();
        intro.setId(id);
        intro.setTitle(param.getTitle());
        intro.setAuthor(param.getAuthor());
        intro.setContent(param.getContent());
        Date now = new Date();
        intro.setCreateTime(now);
        this.introDao.updateByPrimaryKey(intro);
        return ResultT.error();
    }



}
