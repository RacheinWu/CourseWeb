package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.dao.other.CasesDao;
import com.he.ssm.entity.other.Cases;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 案例Service业务层处理
 *
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Service
public class CasesService {
    @Resource
    private CasesDao casesDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Cases>> list(Cases cases) {
        List<Cases> list = this.casesDao.findByAll(cases);
        return ResultT.successWithData(list);
    }

    /**
     * 分页显示案例
     */
    public ResultT<PageInfo<Cases>> page(Cases cases, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (Objects.nonNull(cases.getBeginPublishDate())) {
            cases.setBeginPublishDate(DateUtil.beginOfDay(cases.getBeginPublishDate()));
        }
        if (Objects.nonNull(cases.getEndPublishDate())) {
            cases.setEndPublishDate(DateUtil.endOfDay(cases.getEndPublishDate()));
        }
        List<Cases> list = this.casesDao.findByCondition(cases);
        PageInfo<Cases> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增案例
     */
    public ResultT<Void> add(Cases cases) {
        //判断数据唯一性
        Cases params = new Cases();
        params.setTitile(cases.getTitile());
        params.setAuthor(cases.getAuthor());
        List<Cases> uniqueData = this.casesDao.findByAll(params);
        if (!uniqueData.isEmpty()) {
            return ResultT.errorWithMsg("数据已存在");
        }
        if ("发布".equals(cases.getState())) {
            cases.setPublishDate(new Date());
        }
        cases.setCreateDate(new Date());
        int count = this.casesDao.insert(cases);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改案例
     */
    public ResultT<Void> update(Cases cases) {
        Long id = cases.getId();
        //判断数据唯一性
        Cases params = new Cases();
        params.setTitile(cases.getTitile());
        params.setAuthor(cases.getAuthor());
        List<Cases> uniqueData = this.casesDao.findByAll(params);
        boolean check = uniqueData.size() > 1 || (uniqueData.size() == 1 && !uniqueData.get(0).getId().equals(id));
        if (check) {
            return ResultT.errorWithMsg("数据已存在");
        }
        Date now = new Date();
        if ("未发布".equals(cases.getState())) {
            now = null;
        }
        cases.setPublishDate(now);
        Cases existData = this.casesDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(cases, existData);
        int count = this.casesDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 删除案例
     */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.casesDao.deleteByPrimaryKeyIn(idList);
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
        int count = this.casesDao.updateStateById(state, now, id);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

}
