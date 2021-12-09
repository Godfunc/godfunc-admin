package com.godfunc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@AllArgsConstructor
@ApiModel("登录返回")
public class LoginDTO {

    @ApiModelProperty("令牌")
    private String token;

    @ApiModelProperty("过期时间")
    private LocalDateTime expired;
}
