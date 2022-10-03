package com.he.ssm.entity.other;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 测试代码生成Entity实体
 * @author itaem
 * @date 2021-03-18 11:18:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "国际")
    private String international;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private Long age;
    @ApiModelProperty(value = "生日")
    private Date birthday;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "爱好")
    private String hobby;
    @ApiModelProperty(value = "头像")
    private String photoUrl;
    @ApiModelProperty(value = "简介")
    private String intro;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "生活照")
    private String lifePhoto;
    @ApiModelProperty(value = "其他资料")
    private String others;
}
