package com.he.ssm.redis.myPrefixKey;

import com.he.ssm.redis.BasePrefix;

/**
 * @Author 计算机科学系 吴远健
 * @Date 2022/10/2
 * @Description
 */
public class CountKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = 0;
    public static final String FILE_PREFIX_NAME = "file-";
    public static final String VEDIO_PREFIX_NAME = "vedio-";
    public static final String WEBSITE_PREFIX_NAME = "vedio-";

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
    public static CountKey ATTACK_TOTAL = new CountKey(TOKEN_EXPIRE, FILE_PREFIX_NAME);
    //某个视频的总播放量
    public static CountKey VEDIO_TOTAL = new CountKey(TOKEN_EXPIRE, VEDIO_PREFIX_NAME);
    //某个网页的浏览量
    public static CountKey WEBSITE_TOTAL = new CountKey(TOKEN_EXPIRE, WEBSITE_PREFIX_NAME);


}
