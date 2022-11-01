package com.he.ssm.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 社会实践(附件放在附件表中)Entity实体
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Practice{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "实践名称")
    private String practiceName;
    @ApiModelProperty(value = "封面图片Url")
    private String photoUrl;
    @ApiModelProperty(value = "开始时间")
    private Date startDate;
    @ApiModelProperty(value = "结束时间")
    private Date endDate;
    @ApiModelProperty(value = "队伍名称")
    private String teamName;
    @ApiModelProperty(value = "负责人")
    private String leader;
    @ApiModelProperty(value = "队伍成员")
    private String members;
    @ApiModelProperty(value = "实践简介")
    private String intro;
    @ApiModelProperty(value = "实践描述")
    private String remark;
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
    private Long watchingCount;

}
