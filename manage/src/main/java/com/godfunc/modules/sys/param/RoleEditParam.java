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
@ApiModel("修改角色")
public class RoleEditParam {

    @ApiModelProperty("角色id")
    @NotNull(message = "请选择要修改的角色")
    private Long id;

    @ApiModelProperty("角色名")
    @NotBlank(message = "角色名不能为空")
    private String name;

    @ApiModelProperty("角色备注")
    private String remark;

    @ApiModelProperty("角色拥有的菜单")
    private Long[] menus;
}
