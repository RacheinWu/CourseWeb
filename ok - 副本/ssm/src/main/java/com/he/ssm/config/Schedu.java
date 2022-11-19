package com.he.ssm.config;

import com.he.ssm.dao.other.*;
import com.he.ssm.redis.RedisService;
import com.he.ssm.redis.myPrefixKey.CountKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/8
 * @Description  定时器
 */
@Configuration
@Slf4j
public class Schedu {

    @Autowired
    private RedisService redisService;

    @Autowired
    private VideoDao videoService;

    @Autowired
    private IntroDao introService;

    @Autowired
    private AttachDao attachService;

    @Autowired
    private CasesDao casesDao;

    @Autowired
    private PracticeDao practiceDao;

    /**
     * 每隔 一段时间进行 调用
     * 时间 = 5s
     */
    @Scheduled(cron = "0/5 * * * * ? ")
    public void redis_count_scheduled() {
        //redis data sync to mysql
        if (count_update_sync()) {
//            log.info("redis mysql 数据同步完成!");
        } else {
            log.error("redis mysql sync fail!");
        }
    }

    /**
     * redis data sync to mysql
     * @return boolean
     */
    private boolean count_update_sync() {
        try {
//            //从redis中更新数据
//            InitialConfig.attach_id_count.forEach(attach -> {
//                //从redis中 根据id进行索引出对应的count
//                Long count = redisService.get(CountKey.ATTACK_TOTAL, attach.getId().toString(), Long.class);
//                attach.setDownloadCount(count);
//            });
//            InitialConfig.intro_id_count.forEach(intro -> {
//                Long count = redisService.get(CountKey.INTRO_TOTAL, intro.getId().toString(), Long.class);
//                intro.setWatchingCount(count);
//            });
//            InitialConfig.video_id_count.forEach(video -> {
//                Long count = redisService.get(CountKey.VEDIO_TOTAL, video.getId().toString(), Long.class);
//                video.setWatchingCount(count);
//            });
//            //同步到mysql：
//            attachService.updateBatchById(InitialConfig.attach_id_count);
//            introService.updateBatchById(InitialConfig.intro_id_count);
//            videoService.updateBatchById(InitialConfig.video_id_count);
            //version 3.0:
            Set<String> videoKeys = redisService.getKeysByPrefix("CountKey:" + CountKey.VEDIO_PREFIX_NAME);
            Set<String> introKeys = redisService.getKeysByPrefix("CountKey:" + CountKey.INTRO_PREFIX_NAME);
            Set<String> attachKeys = redisService.getKeysByPrefix("CountKey:" + CountKey.ATTACH_PREFIX_NAME);
            Set<String> caseKeys = redisService.getKeysByPrefix("CountKey:" + CountKey.CASES_PREFIX_NAME);
            Set<String> practiceKeys = redisService.getKeysByPrefix("CountKey:" + CountKey.PRACTICE_PREFIX_NAME);
            //从redis中获取数据
            for (String key : videoKeys) {
                Long count = redisService.get(CountKey.VEDIO_TOTAL, key, Long.class);
                //同步到mysql中
                redisService.delete(CountKey.VEDIO_TOTAL, key);
                videoService.updateCountById(Long.valueOf(key), count);
            }
            //
            for (String key : attachKeys) {
                Long count = redisService.get(CountKey.ATTACK_TOTAL, key, Long.class);
                //同步到mysql中
                redisService.delete(CountKey.ATTACK_TOTAL, key);
                attachService.updateCountById(Long.valueOf(key), count);
            }
            //
            for (String key : introKeys) {
                Long count = redisService.get(CountKey.INTRO_TOTAL, key, Long.class);
                //同步到mysql中
                redisService.delete(CountKey.INTRO_TOTAL, key);
                introService.updateCountById(Long.valueOf(key), count);
            }
            //
            for (String key : caseKeys) {
                Long count = redisService.get(CountKey.CASES_TOTAL, key, Long.class);
                //同步到mysql中
                redisService.delete(CountKey.CASES_TOTAL, key);
                casesDao.updateCountById(Long.valueOf(key), count);
            }
            //
            for (String key : practiceKeys) {
                Long count = redisService.get(CountKey.PRACTICE_TOTAL, key, Long.class);
                //同步到mysql中
                redisService.delete(CountKey.PRACTICE_TOTAL, key);
                practiceDao.updateCountById(Long.valueOf(key), count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
