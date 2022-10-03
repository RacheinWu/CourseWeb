package com.he.ssm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author itaem
 * date:2021-02-05 2021/2/5 0005:14:51
 */
@Data
@Component
@ConfigurationProperties(prefix = "he.upload")
public class UploadConfig {
    /**
     * 上传目录
     */
    private String   dir;
    /**
     * 上传目录网络路径
     */
    private String   dirResourcePath;
    /**
     * 允许的后缀名
     */
    private String   allowExt;
    /**
     * 允许的最大文件大小
     */
    private Integer allowMaxFileSize;
    /**
     * 允许的最大文件名长度
     */
    private Integer  allowMaxFileNameLength;
    /**
     * 网址前缀
     */
    private String   ctx;

    public String getHttpPrefix(){
        return ctx + dirResourcePath + "/";
    }
    public String getAbsolutePrefix(){
        return dir  + File.separator ;
    }
}
