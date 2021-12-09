package com.godfunc.modules.sys.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@ApiModel("修改密码")
public class UserPasswordParam {

    @ApiModelProperty("原密码")
    @NotBlank(message = "原密码不能为空")
    private String password;

    @ApiModelProperty("新密码")
    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @ApiModelProperty("确认新密码")
    @NotBlank(message = "确认新密码不能为空")
    private String confirmNewPassword;

}
