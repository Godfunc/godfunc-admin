package com.godfunc.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mg_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 父菜单id，0表示当前为根菜单
     */
    private Long pid;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 1菜单
     * 2按钮
     */
    private Integer type;

    /**
     * 跳转地址 默认noRedirect不跳转
     */
    private String redirect;

    /**
     * the name is used by <keep-alive> (must set!!!)
     */
    private String name;

    /**
     * 只有一个子菜单时是否显示主菜单
     */
    private Boolean alwaysShow;

    /**
     * 菜单权限
     */
    private String permissions;

    /**
     * 0隐藏面包屑 1不隐藏面包屑
     */
    private Boolean breadcrumb;

    /**
     * 如果设置，将高亮指定菜单
     */
    private String activeMenu;

    /**
     * 标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    private Integer sort;

    private Integer status;
}
