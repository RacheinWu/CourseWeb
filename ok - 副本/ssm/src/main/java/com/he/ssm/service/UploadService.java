package com.he.ssm.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.he.ssm.bean.FileInfo;
import com.he.ssm.bean.ResultT;
import com.he.ssm.config.UploadConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author itaem
 * date:2020-08-25 2020/8/25:16:02
 */
@Slf4j
@Service
public class UploadService {
    @Resource
    private UploadConfig uploadConfig;

    /**
     * 单文件上传
     */
    public ResultT<FileInfo> upload(MultipartFile file) {
        try {
            log.warn("{}    {}  {}  {}", file.getOriginalFilename(), file.getSize(), file.getContentType(), file.getName());
            FileInfo fileInfo = new FileInfo();
            String fileName = file.getOriginalFilename();
            if (StringUtils.isBlank(fileName)) {
                fileInfo.setErrMsg("上传文件名获取失败");
                //return ResultT.errorWithMsg("上传文件名获取失败");
            }
            int fileNameLength = fileName.length();
            String extName = FileUtil.extName(fileName);
            String mimeType = FileUtil.getMimeType(fileName);
            long fileSize = file.getSize();
            fileInfo.setFileName(fileName);
            fileInfo.setFileSize(fileSize);
            fileInfo.setMimeType(mimeType);
            fileInfo.setExtName(extName);

            if (!uploadConfig.getAllowExt().contains(extName)) {
                fileInfo.setErrMsg("不允许上传" + extName + "结尾的文件");
                return ResultT.errorWithData(fileInfo);
                //return ResultT.errorWithMsg("不允许上传" + extName + "结尾的文件");
            }
            if (uploadConfig.getAllowMaxFileSize() < fileSize) {
                fileInfo.setErrMsg("文件大小超过限制，最大为：" + uploadConfig.getAllowMaxFileSize());
                return ResultT.errorWithData(fileInfo);
                //return ResultT.errorWithMsg("文件大小超过限制，最大为：" + uploadConfig.getAllowMaxFileSize());
            }
            if (uploadConfig.getAllowMaxFileNameLength() < fileNameLength) {
                fileInfo.setErrMsg("文件名长度超过限制，最大为：" + uploadConfig.getAllowMaxFileNameLength());
                return ResultT.errorWithData(fileInfo);
                //return ResultT.errorWithMsg("文件名长度超过限制，最大为：" + uploadConfig.getAllowMaxFileNameLength());
            }
            Date now = new Date();
            String dateDir = DateUtil.format(now, "yyyy/MM/dd");
            String dateTimeFileName = DateUtil.format(now, "HHmmssSSS");
            String storeFileName = dateTimeFileName + "_" + fileName;
            File storeDir = new File(uploadConfig.getDir(), dateDir);
            if (!storeDir.exists()) {
                storeDir.mkdirs();
            }
            String relativePath = dateDir + "/" + storeFileName;
            fileInfo.setRelativePath(relativePath);
            fileInfo.setNetworkPath(uploadConfig.getCtx() + uploadConfig.getDirResourcePath() + "/" + relativePath);
            File destFile = new File(storeDir, storeFileName);
            file.transferTo(destFile);
            return ResultT.successWithData(fileInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultT.error();
    }


    /**
     * 多文件上传
     */
    public ResultT<List<FileInfo>> uploads(List<MultipartFile> files) {
        List<FileInfo> list = new ArrayList<>();
        for (MultipartFile file : files) {
            ResultT<FileInfo> upload = this.upload(file);
            list.add(upload.getData());
        }
        return ResultT.successWithData(list);
    }
}
