package com.he.ssm.config;

import com.he.ssm.entity.other.Attach;
import com.he.ssm.entity.other.Intro;
import com.he.ssm.entity.other.Video;
import com.he.ssm.redis.RedisService;
import com.he.ssm.redis.myPrefixKey.CountKey;
import com.he.ssm.service.other.AttachService;
import com.he.ssm.service.other.IntroService;
import com.he.ssm.service.other.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/8
 * @Description 初始化类
 */
@Configuration
@Slf4j
public class InitialConfig {

    @Autowired
    private RedisService redisService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private IntroService introService;

    @Autowired
    private AttachService attachService;

//    public static Map<String, Map<Long,Long>> countMap = new HashMap<>();
//    public static Map<String, List> countMap = new HashMap<>();
    public static List<Video> video_id_count;
    public static List<Intro> intro_id_count;
    public static List<Attach> attach_id_count;

    /**
     * 服务器开启 自动执行
     */
    @Bean
    public void init() {
//        if (redis_mysql_sync_init()) {
//            log.info(">>>>>>>>>>>>>>>>>>>>>>>缓存同步成功！");
//        }else {
//            log.error(">>>>>>>>?>>>>>>>>>>?>>>同步失败!");
//        }
    }

    /**
     * redis | mysql 数据同步
     */
    private boolean redis_mysql_sync_init() {
        //先获取每个模块数据库的id
        try {
//            Map<Long, Long> video_id_count = videoService.lambdaQuery().select(Video::getId, Video::getWatchingCount).list().stream().collect(Collectors.toMap(Video::getId, Video::getWatchingCount));
//            Map<Long, Long> intro_id_count = introService.lambdaQuery().select(Intro::getId, Intro::getWatchingCount).list().stream().collect(Collectors.toMap(Intro::getId, Intro::getWatchingCount));
//            Map<Long, Long> attach_id_count = attachService.lambdaQuery().select(Attach::getId, Attach::getDownloadCount).list().stream().collect(Collectors.toMap(Attach::getId, Attach::getDownloadCount));
//            countMap.put("video_id_count", video_id_count);
//            countMap.put("intro_id_count", intro_id_count);
//            countMap.put("attach_id_count", attach_id_count);
//            //同步到redis中，进行存储:
//            video_id_count.forEach((k,v) -> {
//                redisService.set(CountKey.VEDIO_TOTAL, k.toString(), v);
//            });
//            intro_id_count.forEach((k,v) -> {
//                redisService.set(CountKey.INTRO_TOTAL, k.toString(), v);
//            });
//            attach_id_count.forEach((k,v) -> {
//                redisService.set(CountKey.ATTACK_TOTAL, k.toString(), v);
//            });
//            video_id_count = videoService.lambdaQuery().select(Video::getId, Video::getWatchingCount).list();
//            intro_id_count = introService.lambdaQuery().select(Intro::getId, Intro::getWatchingCount).list();
//            attach_id_count = attachService.lambdaQuery().select(Attach::getId, Attach::getDownloadCount).list();
//            video_id_count.forEach(o -> {
//                redisService.set(CountKey.VEDIO_TOTAL, o.getId().toString(), o.getWatchingCount());
//            });
//            intro_id_count.forEach(o -> {
//                redisService.set(CountKey.INTRO_TOTAL, o.getId().toString(), o.getWatchingCount());
//            });
//            attach_id_count.forEach(o -> {
//                redisService.set(CountKey.ATTACK_TOTAL, o.getId().toString(), o.getDownloadCount());
//            });
//            countMap.put("video_id_count", video_id_count);
//            countMap.put("intro_id_count", intro_id_count);
//            countMap.put("attach_id_count", attach_id_count);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
