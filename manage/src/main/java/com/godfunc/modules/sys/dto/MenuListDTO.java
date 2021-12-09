package com.godfunc.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ApiModel("菜单列表")
public class MenuListDTO extends TreeModel<MenuListDTO> implements Serializable {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long pid;

    /**
     * 路由地址
     */
    @ApiModelProperty("路由地址")
    private String path;

    /**
     * 组件
     */
    @ApiModelProperty("组件")
    private String component;

    /**
     * 跳转地址 默认noRedirect不跳转
     */
    @ApiModelProperty("跳转地址")
    private String redirect;

    /**
     * the name is used by <keep-alive> (must set!!!)
     */
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("一直显示")
    private Boolean alwaysShow;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("图标")
    private String icon;

    /**
     * 0隐藏面包屑 1不隐藏面包屑
     */
    @ApiModelProperty("是否显示面包屑，true显示 false不显示")
    private Boolean breadcrumb;

    /**
     * 如果设置，将高亮指定菜单
     */
    @ApiModelProperty("激活菜单")
    private String activeMenu;

    @ApiModelProperty("子菜单")
    private List<MenuListDTO> children = new ArrayList<>();
}
