package com.he.ssm.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author itaem
 * date:2020-12-31 2020/12/31:22:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfilePwdReqDto {
    @ApiModelProperty(value = "id")
    private Long       id;
    @ApiModelProperty(value = "旧密码")
    private String     oldPassword;
    @ApiModelProperty(value = "新密码")
    private String     newPassword;
}
