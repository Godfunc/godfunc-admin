package com.godfunc.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@ApiModel("角色列表")
public class RoleListDTO implements Serializable {

    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty("角色名")
    private String name;
}
