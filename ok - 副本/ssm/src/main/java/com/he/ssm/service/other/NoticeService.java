package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.dao.other.NoticeDao;
import com.he.ssm.entity.other.Notice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 公告Service业务层处理
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class NoticeService{
    @Resource
    private NoticeDao noticeDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Notice>> list(Notice notice) {
        List<Notice> list = this.noticeDao.findByAll(notice);
        return ResultT.successWithData(list);
    }
    /**
     * 分页显示公告
     */
    public ResultT<PageInfo<Notice>> page(Notice notice,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if(Objects.nonNull(notice.getBeginPublishDate())){
            notice.setBeginPublishDate(DateUtil.beginOfDay( notice.getBeginPublishDate()));
        }
        if(Objects.nonNull(notice.getEndPublishDate())){
            notice.setEndPublishDate(DateUtil.endOfDay( notice.getEndPublishDate()));
        }
        List<Notice> list=this.noticeDao.findByCondition(notice);
        PageInfo<Notice> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增公告
     */
    public ResultT<Void> add(Notice notice){
        //判断数据唯一性
        Notice params=new Notice();
        params.setTitle(notice.getTitle());
        params.setPublishOrg(notice.getPublishOrg());
        List<Notice> uniqueData = this.noticeDao.findByAll(params);
        if (!uniqueData.isEmpty()) {
            return ResultT.errorWithMsg("数据已存在");
        }
        if ("发布".equals(notice.getState())) {
            notice.setPublishDate(new Date());
        }
        notice.setCreateDate( new Date());
        int count = this.noticeDao.insert(notice);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改公告
     */
    public ResultT<Void> update(Notice notice) {
        Long id = notice.getId();
        //判断数据唯一性
        Notice params=new Notice();
        params.setTitle(notice.getTitle());
        params.setPublishOrg(notice.getPublishOrg());
        List<Notice> uniqueData = this.noticeDao.findByAll(params);
        boolean check = uniqueData.size() > 1 || (uniqueData.size() == 1 && !uniqueData.get(0).getId().equals(id));
        if (check) {
            return ResultT.errorWithMsg("数据已存在");
        }
        Notice existData = this.noticeDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(notice, existData);
        int count = this.noticeDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
    * 删除公告
    */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.noticeDao.deleteByPrimaryKeyIn(idList);
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
        int count = this.noticeDao.updateStateById(state, now, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

}
