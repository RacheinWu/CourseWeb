package com.he.ssm.entity.other;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 轮播图Entity实体
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "图片Url")
    private String photoUrl;
    @ApiModelProperty(value = "链接地址")
    private String link;
    @ApiModelProperty(value = "是否显示")
    private String showFlag;
    @ApiModelProperty(value = "顺序")
    private Long orderNum;
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
