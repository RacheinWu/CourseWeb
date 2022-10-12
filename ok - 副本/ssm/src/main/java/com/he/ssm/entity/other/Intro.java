package com.he.ssm.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


/**
 * 介绍Entity实体
 * @author 梁灿健
 * @date 2021-03-08 23:47:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Intro {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "author")
    private String author;
    @ApiModelProperty(value = "content")
    private String content;
    @ApiModelProperty(value = "createTime")
    private Date createTime;
    @ApiModelProperty(value = "type")
    private String type;
    @ApiModelProperty(value = "title")
    private String title;
    @JsonIgnore
    private transient Date beginPublishDate;
    @JsonIgnore
    private transient Date endPublishDate;
    private Long watchingCount;
}


