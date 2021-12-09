package com.godfunc.modules.sys.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@ApiModel("新增菜单")
public class MenuAddParam {

    @ApiModelProperty("上级菜单id")
    private Long pid;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("类型，1菜单 2按钮")
    @NotNull(message = "请选择菜单类型")
    private Integer type;

    @ApiModelProperty("跳转地址")
    private String redirect;

    @ApiModelProperty("一直显示")
    private Boolean alwaysShow;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("权限")
    private String permissions;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态，0停用 1启用")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("是否显示面包屑，true显示 false不显示")
    private Boolean breadcrumb;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("激活菜单")
    private String activeMenu;
}
