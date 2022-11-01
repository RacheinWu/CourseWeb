package com.he.ssm.config;

import com.he.ssm.dao.other.AttachDao;
//import com.he.ssm.dao.other.MyDao;
import com.he.ssm.entity.other.Attach;
import com.he.ssm.entity.other.Video;
import com.he.ssm.redis.RedisService;
import com.he.ssm.redis.myPrefixKey.CountKey;
import com.he.ssm.service.other.AttachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/3
 * @Description
 */
@Slf4j
@Component
public class FileDownloadRecordInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Autowired
    private AttachDao attachDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //针对下载链接格式的url进行拦截：static/res/**
        String requestURI = request.getRequestURI();
        if (requestURI.contains("static/res/")) {
            try {
                //视频
                if (requestURI.contains(".mp4")) {
                    //redis + 1
                    redisService.incr(CountKey.VEDIO_TOTAL, requestURI.substring(1));
                }
                //附件
                else {
                    //redis + 1
                    String url = requestURI.replace("/static/res/", "");
                    Attach attach = attachDao.getIdAndCountByRU(url);
                    if (!redisService.exists(CountKey.ATTACK_TOTAL, attach.getId().toString())) {
                        redisService.set(CountKey.ATTACK_TOTAL, attach.getId().toString(), attach.getDownloadCount());
                    }
                    redisService.incr(CountKey.ATTACK_TOTAL, attach.getId().toString());
                }
            } catch (Exception e) {
                log.warn("找不到对应的文件...");
                e.printStackTrace();
            }
        }
        return true;
    }
}
