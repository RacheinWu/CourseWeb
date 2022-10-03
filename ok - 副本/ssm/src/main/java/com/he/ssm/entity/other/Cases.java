package com.he.ssm.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 案例Entity实体
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cases{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "标题")
    private String titile;
    @ApiModelProperty(value = "作者")
    private String author;
    @ApiModelProperty(value = "发布人")
    private String publisher;
    @ApiModelProperty(value = "文本内容")
    private String content;
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
