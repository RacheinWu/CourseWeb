package com.he.ssm.dao;

import com.he.ssm.dao.other.AttachDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

/**
 * @author itaem
 * date:2021-03-06 2021/3/6:0:40
 */
@Slf4j
@SpringBootTest
class UserDaoTest {
    //@Resource
    //private UserDao userDao;
    //@Test
    //public void test()throws Exception {
    //    this.userDao.findByNameLike(null);
    //}

    @Autowired
    private AttachDao attachDao;



}