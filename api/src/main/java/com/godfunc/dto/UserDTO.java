package com.godfunc.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 * @date 2020/7/31
 */
@Data
@ApiModel("用户信息")
public class UserDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
