package com.he.ssm.entity.other;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 留言标题Entity实体
 * @author itaem
 * @date 2021-03-21 14:52:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msgtitle{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
}
