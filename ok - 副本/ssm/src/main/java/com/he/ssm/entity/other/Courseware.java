package com.he.ssm.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 课件(其他附件在附件表中)Entity实体
 * @author itaem
 * @date 2021-03-08 23:47:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courseware{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "类型")
    private String coursewareType;
    @ApiModelProperty(value = "标题")
    private String tile;
    @ApiModelProperty(value = "作者")
    private String author;
    @ApiModelProperty(value = "课件封面图URL")
    private String coursewareUrl;
    @ApiModelProperty(value = "是否可下载")
    private String downloadFlag;
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
