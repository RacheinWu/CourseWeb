package com.he.ssm.controller;

import com.he.ssm.bean.FileInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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

}