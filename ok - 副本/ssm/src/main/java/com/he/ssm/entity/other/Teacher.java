package com.he.ssm.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 教师Entity实体
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "类型(名师队伍:famous,负责人:leader)")
    private String teacherType;
    @ApiModelProperty(value = "名称")
    private String teacherName;
    @ApiModelProperty(value = "职称")
    private String title;
    @ApiModelProperty(value = "职务")
    private String post;
    @ApiModelProperty(value = "email")
    private String email;
    @ApiModelProperty(value = "头像URL")
    private String photoUrl;
    @ApiModelProperty(value = "文本内容")
    private String content;
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
    @ApiModelProperty(value = "发布时间")
    private Date publishDate;
    @ApiModelProperty(value = "荣誉标识")
    private String honor;
    @JsonIgnore
    private transient Date beginPublishDate;
    @JsonIgnore
    private transient Date endPublishDate;
}
