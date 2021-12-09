package com.godfunc.modules.security.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@ApiModel("登录表单")
public class LoginParam {

    @ApiModelProperty("用户名")
    @NotBlank(message = "请输入用户名")
    private String username;

    @ApiModelProperty("登录密码")
    @NotBlank(message = "请输入登录密码")
    private String password;

    @ApiModelProperty("验证码uuid")
    @NotBlank(message = "请输入验证码")
    private String uuid;

    @ApiModelProperty("验证码")
    @NotBlank(message = "请输入验证码")
    private String captcha;
}
