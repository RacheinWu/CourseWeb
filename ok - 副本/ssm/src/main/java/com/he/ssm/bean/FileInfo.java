package com.he.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author itaem
 * date:2021-01-14 2021/1/14 0014:18:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    private Long id;
    private String fileName;
    private Long fileSize;
    private String mimeType;
    private String extName;
    private String relativePath;
    private String networkPath;
    private String errMsg;
    private Integer downloadCount;

    public FileInfo(String fileName, Long fileSize, String mimeType, String extName, String relativePath, Integer downloadCount) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.mimeType = mimeType;
        this.extName = extName;
        this.relativePath = relativePath;
        this.downloadCount = downloadCount;
    }
}
