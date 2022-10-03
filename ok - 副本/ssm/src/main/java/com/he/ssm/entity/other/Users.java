package com.he.ssm.entity.other;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * 用户Entity实体
 * @author itaem
 * @date 2021-03-08 23:47:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users{
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "类型(admin:admin,老师:teacher)")
    private String userType;
    @ApiModelProperty(value = "账号/姓名")
    private String account;
    @ApiModelProperty(value = "密码")
    private String pwd;
    @ApiModelProperty(value = "年龄")
    private Long age;
    @ApiModelProperty(value = "手机")
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "头像Url")
    private String avatar;
    @ApiModelProperty(value = "状态")
    private String state;
    @ApiModelProperty(value = "创建时间")
    private Date createDate;
}
