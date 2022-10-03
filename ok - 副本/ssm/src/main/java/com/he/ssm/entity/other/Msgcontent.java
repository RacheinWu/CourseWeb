package com.he.ssm.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 留言内容Entity实体
 * @author itaem
 * @date 2021-03-21 14:52:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msgcontent{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "所属标题Id")
    private Long titleId;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "类型(发起:发起 回复:回复)")
    private String msgType;
    @ApiModelProperty(value = "留言内容")
    private String content;
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @JsonIgnore
    private transient Date beginCreateDate;
    @JsonIgnore
    private transient Date endCreateDate;
}
