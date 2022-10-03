package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.dao.other.MsgtitleDao;
import com.he.ssm.entity.other.Msgtitle;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 留言标题Service业务层处理
 *
 * @author itaem
 * @date 2021-03-21 14:52:17
 */
@Service
public class MsgtitleService {
    @Resource
    private MsgtitleDao   msgtitleDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Msgtitle>> list(Msgtitle msgtitle) {
        List<Msgtitle> list = this.msgtitleDao.findUnique(msgtitle);
        return ResultT.successWithData(list);
    }

    /**
     * 分页显示留言标题
     */
    public ResultT<PageInfo<Msgtitle>> page(Msgtitle msgtitle, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Msgtitle> list = this.msgtitleDao.findByCondition(msgtitle);
        PageInfo<Msgtitle> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增留言标题
     */
    public ResultT<Void> add(Msgtitle msgtitle) {
        msgtitle.setCreateDate(new Date());
        int count = this.msgtitleDao.insert(msgtitle);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改留言标题
     */
    public ResultT<Void> update(Msgtitle msgtitle) {
        Long id = msgtitle.getId();
        Msgtitle existData = this.msgtitleDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(msgtitle, existData);
        int count = this.msgtitleDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 删除留言标题
     */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.msgtitleDao.deleteByPrimaryKeyIn(idList);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    public ResultT<Void> updateState(Long id, String state) {
        int count = this.msgtitleDao.updateStateById(state, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }


}
