package com.godfunc.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author godfunc
 */
@Data
@ApiModel("用户信息")
public class UserInfoDTO implements Serializable {

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("权限")
    private Set<String> permissions;
}
