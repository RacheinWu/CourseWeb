package com.he.ssm;

import cn.hutool.core.date.DateUtil;
import com.he.ssm.dao.other.AttachDao;
import com.he.ssm.dao.other.VideoDao;
import com.he.ssm.service.api.ApiService;
import com.he.ssm.service.other.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * @author itaem
 * date:2021-03-13 2021/3/13:15:20
 */
@Slf4j
class RunTest {

    @Test
    public void test() throws Exception {
        for (int i = 0; i < 7; i++) {
            
        }
        log.warn("{}",DateUtil.hour(new Date(),true));
        String format = DateUtil.format(new Date(), "HHmmssSSS");
        log.info("{}", Integer.valueOf(format));
        Date now = new Date();
        now=DateUtil.offsetDay(now,2);
        log.info("{}    {}",dayOfWeek(DateUtil.dayOfWeek(now)), DateUtil.dayOfWeek(now));
    }

    public String dayOfWeek(int day) {
        String result = "";
        switch (day) {
            case 1:
                result = "星期天";
                break;
            case 2:
                result = "星期一";
                break;

            case 3:
                result = "星期二";
                break;
            case 4:
                result = "星期三";
                break;
            case 5:
                result = "星期四";
                break;
            case 6:
                result = "星期五";
                break;
            case 7:
                result = "星期六";
                break;

        }
        return result;
    }

    @Test
    public void t12() {
        String s = "http://210.38.139.237:9091/static/res/2022/01/05/132634966_%E6%95%99%E5%AD%A6%E8%B5%84%E6%BA%90.docx";
        System.out.println(s.contains("static/res/"));
    }
}