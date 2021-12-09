package com.godfunc.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@ApiModel("注册")
public class RegisterParam {

    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("确认密码")
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;
}
