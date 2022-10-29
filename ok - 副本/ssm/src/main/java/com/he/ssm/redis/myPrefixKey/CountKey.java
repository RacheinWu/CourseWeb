package com.he.ssm.redis.myPrefixKey;

import com.he.ssm.redis.BasePrefix;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description
 */
public class CountKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 10;
    public static final String ATTACH_PREFIX_NAME = "file-";
    public static final String VEDIO_PREFIX_NAME = "vedio-";
    public static final String WEBSITE_PREFIX_NAME = "vedio-";
    public static final String INTRO_PREFIX_NAME = "intro-";
    public static final String CASE_PREFIX_NAME = "intro-";
    public static final String PRACTICE_PREFIX_NAME = "practice-";

    /**
     * 防止被外面实例化
     * @param expireSeconds
     * @param prefix
     */
    private CountKey(int expireSeconds, String prefix) {super(expireSeconds, prefix);}

    /**
     * 需要缓存的字段：
     */
    //某个附件的总浏览次数
    public static CountKey ATTACK_TOTAL = new CountKey(TOKEN_EXPIRE, ATTACH_PREFIX_NAME);
    //某个视频的总播放量
    public static CountKey VEDIO_TOTAL = new CountKey(TOKEN_EXPIRE, VEDIO_PREFIX_NAME);
    //某个网页的浏览量
    public static CountKey WEBSITE_TOTAL = new CountKey(TOKEN_EXPIRE, WEBSITE_PREFIX_NAME);
    //某个intro信息的浏览量
    public static CountKey INTRO_TOTAL = new CountKey(TOKEN_EXPIRE, INTRO_PREFIX_NAME);
    //某个信息的浏览量
    public static CountKey CASE_TOTAL = new CountKey(TOKEN_EXPIRE, CASE_PREFIX_NAME);
    //某个practice的浏览量
    public static CountKey PRACTICE_TOTAL = new CountKey(TOKEN_EXPIRE, PRACTICE_PREFIX_NAME);
    //

}
