package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.dao.other.MsgcontentDao;
import com.he.ssm.entity.other.Msgcontent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 留言内容Service业务层处理
 *
 * @author itaem
 * @date 2021-03-21 14:52:17
 */
@Service
public class MsgcontentService {
    @Resource
    private MsgcontentDao msgcontentDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Msgcontent>> list(Msgcontent msgcontent) {
        List<Msgcontent> list = this.msgcontentDao.findUnique(msgcontent);
        return ResultT.successWithData(list);
    }

    /**
     * 分页显示留言内容
     */
    public ResultT<PageInfo<Msgcontent>> page(Msgcontent msgcontent, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (Objects.nonNull(msgcontent.getBeginCreateDate())) {
            msgcontent.setBeginCreateDate(DateUtil.beginOfDay(msgcontent.getBeginCreateDate()));
        }
        if (Objects.nonNull(msgcontent.getEndCreateDate())) {
            msgcontent.setEndCreateDate(DateUtil.endOfDay(msgcontent.getEndCreateDate()));
        }
        List<Msgcontent> list = this.msgcontentDao.findByCondition(msgcontent);
        PageInfo<Msgcontent> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增留言内容
     */
    public ResultT<Void> add(Msgcontent msgcontent) {
        msgcontent.setCreateDate(new Date());
        if (StringUtils.isBlank(msgcontent.getState())) {
            msgcontent.setState("未审核");
        }

        int count = this.msgcontentDao.insert(msgcontent);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改留言内容
     */
    public ResultT<Void> update(Msgcontent msgcontent) {
        Long id = msgcontent.getId();
        Msgcontent existData = this.msgcontentDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(msgcontent, existData);
        int count = this.msgcontentDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 删除留言内容
     */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.msgcontentDao.deleteByPrimaryKeyIn(idList);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    public ResultT<Void> updateState(Long id, String state) {
        int count = this.msgcontentDao.updateStateById(state, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }
}
