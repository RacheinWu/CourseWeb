package com.he.ssm.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 公告Entity实体
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "发布单位")
    private String publishOrg;
    @ApiModelProperty(value = "文本内容")
    private String content;
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
