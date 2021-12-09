package com.godfunc.modules.security.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("登录成功返回")
public class LoginDTO implements Serializable {

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("token过期时间")
    private String expire;
}
