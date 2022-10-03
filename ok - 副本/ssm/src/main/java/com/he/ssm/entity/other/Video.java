package com.he.ssm.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 视频(其他附件)Entity实体
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "类型")
    private String videoType;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "授课老师")
    private String teacher;
    @ApiModelProperty(value = "封面URL")
    private String photoUrl;
    @ApiModelProperty(value = "视频URL")
    private String videoUrl;
    @ApiModelProperty(value = "视频描述")
    private String remark;
    @ApiModelProperty(value = "上一篇Id")
    private Long lastId;
    @ApiModelProperty(value = "上一篇标题")
    private String lastTitle;
    @ApiModelProperty(value = "下一篇Id")
    private Long nextId;
    @ApiModelProperty(value = "下一篇标题")
    private String nextTitle;
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "发布时间")
    private Date publishDate;
    @JsonIgnore
    private transient Date beginPublishDate;
    @JsonIgnore
    private transient Date endPublishDate;
}
