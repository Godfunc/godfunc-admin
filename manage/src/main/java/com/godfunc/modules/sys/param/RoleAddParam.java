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
@ApiModel("新增角色")
public class RoleAddParam {

    @ApiModelProperty("角色名")
    @NotBlank(message = "角色名不能为空")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("角色拥有的菜单")
    private Long[] menus;
}
