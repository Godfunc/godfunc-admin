package com.godfunc.modules.sys.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@ApiModel("修改用户")
public class UserEditParam {

    @ApiModelProperty("用户id")
    @NotNull(message = "请选择要修改的用户")
    private Long id;

    @ApiModelProperty("用户名")
    @NotBlank(message = "请输入用户名")
    private String username;

    @ApiModelProperty("性别 1男 2女 3未知")
    private Integer gender;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("超级管理员 0不是 1是")
    private Integer superManager;

    @ApiModelProperty("状态 0停用 1启用")
    @NotNull(message = "请输入设置用户状态")
    private Integer status;

    @ApiModelProperty("登录密码")
    private String password;

    @ApiModelProperty("确认登录密码")
    private String confirmPassword;

    @ApiModelProperty("角色")
    private Long[] roles;
}
