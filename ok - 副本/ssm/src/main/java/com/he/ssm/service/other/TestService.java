package com.he.ssm.service.other;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.dao.other.TestDao;
import com.he.ssm.entity.other.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试代码生成Service业务层处理
 * @author itaem
 * @date 2021-03-18 11:18:17
 */
@Service
public class TestService{
    @Resource
    private TestDao testDao;

    /**
     * 查询菜单数据
     */
    public ResultT<List<Test>> list(Test test) {
        List<Test> list = this.testDao.findUnique(test);
        return ResultT.successWithData(list);
    }
    /**
     * 分页显示测试代码生成
     */
    public ResultT<PageInfo<Test>> page(Test test,Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Test> list=this.testDao.findByCondition(test);
        PageInfo<Test> of = PageInfo.of(list);
        return ResultT.successWithData(of);
    }

    /**
     * 新增测试代码生成
     */
    public ResultT<Void> add(Test test){
        int count = this.testDao.insert(test);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
     * 修改测试代码生成
     */
    public ResultT<Void> update(Test test) {
        Long id = test.getId();
        Test existData = this.testDao.selectByPrimaryKey(id);
        BeanUtil.copyProperties(test, existData);
        int count = this.testDao.updateByPrimaryKey(existData);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

    /**
    * 删除测试代码生成
    */
    public ResultT<Void> del(List<Long> idList) {
        int count = this.testDao.deleteByPrimaryKeyIn(idList);
        if (count > 0) {
            return ResultT.success();
        }
        return ResultT.error();
    }

}
