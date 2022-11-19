package com.he.ssm.controller;

import com.he.ssm.bean.FileInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.config.UploadConfig;
import com.he.ssm.dao.other.AttachDao;
import com.he.ssm.entity.other.Attach;
import com.he.ssm.redis.RedisService;
import com.he.ssm.redis.myPrefixKey.CountKey;
import com.he.ssm.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author itaem
 * date:2020-08-26 2020/8/26:9:48
 */
@Slf4j
@Api(tags = "文件上传接口")
@RestController
public class UploadController {
    @Resource
    private UploadService uploadService;

    @Resource
    private AttachDao attachDao;

    @Autowired
    private RedisService redisService;

    @Resource
    private UploadConfig uploadConfig;

    @ApiOperation("单文件上传")
    @PostMapping("/file/upload")
    public ResultT<FileInfo> upload(@RequestParam("file") MultipartFile file) {
        return this.uploadService.upload(file);
    }

    @ApiOperation("多文件上传")
    @PostMapping("/file/uploads")
    public ResultT<List<FileInfo>> uploads(@RequestParam("files") List<MultipartFile> files) {
        return this.uploadService.uploads(files);
    }

    @ApiOperation("下载课件")
    @GetMapping("/cases/{s1}/{s2}/{p1}/{p2}/{p3}/{p4}")
    public void downloadAttach(HttpServletRequest request ,HttpServletResponse response,
                               @PathVariable String s1,
                               @PathVariable String s2,
                               @PathVariable String p1,
                               @PathVariable String p2,
                               @PathVariable String p3,
                               @PathVariable String p4

    ) throws UnsupportedEncodingException {

//        String requestURI2 = request.getRequestURI();
        String dir = uploadConfig.getDir();
        String relativePath = p1 + "/" + p2 + "/" + p3 + "/" + p4;  //数据库相对地址
        String requestURI = URLDecoder.decode(dir + "/" + relativePath, "UTF-8"); //硬盘位置:/upload/2021/01/31/dwaw.word
        log.info(relativePath);
        log.info(requestURI);
        try {
            //redis + 1
            String filePath = requestURI.replace("/cases/static/res/", "");
            log.info("【url = 】" + filePath);

            File file = new File(filePath);
            if (file.exists()) {//判断文件是否存在
                log.info(requestURI + "文件存在");

                try {
                    Attach attach = attachDao.getIdAndCountByRU(relativePath);
                    log.info(attach.toString());
                    if (!redisService.exists(CountKey.ATTACK_TOTAL, attach.getId().toString())) {
                        redisService.set(CountKey.ATTACK_TOTAL, attach.getId().toString(), attach.getDownloadCount());
                    }
                    redisService.incr(CountKey.ATTACK_TOTAL, attach.getId().toString());
                } catch (Exception e) {
                    log.error("redis->文件不存在！");
                }


                String fileName = p4;
                try {
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    log.error("文件名编码失败" + e.toString());
                }//encode编码UTF-8 解决大多数中文乱码
                fileName = fileName.replace("+", "%20");//encode后替换空格  解决空格问题
                response.setContentType("application/force-download");//设置强制下载
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);//设置文件名
                byte[] buff = new byte[1024];// 用来存储每次读取到的字节数组
                //创建输入流（读文件）输出流（写文件）
                BufferedInputStream bis = null;
                OutputStream os = null;
                try {
                    os = response.getOutputStream();
                    bis = new BufferedInputStream(new FileInputStream(file));
                    int i = bis.read(buff);
                    while (i != -1) {
                        os.write(buff, 0, buff.length);
                        os.flush();
                        i = bis.read(buff);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                log.info("文件不存在.......");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}