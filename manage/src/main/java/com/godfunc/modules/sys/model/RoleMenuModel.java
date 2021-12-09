package com.godfunc.modules.sys.model;

import lombok.Data;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
public class RoleMenuModel {

    private Long roleId;

    private Long menuId;

    private String permissions;
}
