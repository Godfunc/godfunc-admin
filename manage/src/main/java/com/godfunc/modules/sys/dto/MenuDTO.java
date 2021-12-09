package com.godfunc.modules.sys.dto;

import com.godfunc.model.TreeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("菜单分页")
public class MenuDTO extends TreeModel<MenuDTO> implements Serializable {

    @ApiModelProperty("菜单id")
    private Long id;

    @ApiModelProperty("上级菜单id")
    private Long pid;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件")
    private String component;

    @ApiModelProperty("类型，1菜单 2按钮")
    private Integer type;

    /**
     *  默认noRedirect不跳转
     */
    @ApiModelProperty("跳转地址")
    private String redirect;

    /**
     * the name is used by <keep-alive> (must set!!!)
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 只有一个子菜单时是否显示主菜单
     */
    @ApiModelProperty("一直显示")
    private Boolean alwaysShow;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图标")
    private String icon;


    @ApiModelProperty("是否显示面包屑，true显示 false不显示")
    private Boolean breadcrumb;


    @ApiModelProperty("激活菜单")
    private String activeMenu;


    @ApiModelProperty("权限")
    private String permissions;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态，0停用 1启用")
    private Integer status;


    @ApiModelProperty("子菜单")
    private List<MenuDTO> children = new ArrayList<>();
}
