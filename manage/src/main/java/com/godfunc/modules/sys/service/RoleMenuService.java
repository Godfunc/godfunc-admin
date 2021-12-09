package com.godfunc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.modules.sys.entity.RoleMenu;
import com.godfunc.modules.sys.model.RoleMenuModel;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface RoleMenuService extends IService<RoleMenu> {

    List<RoleMenuModel> getRoleMenu(Set<Long> roles);

    List<RoleMenuModel> getAllRoleMenu();

    void saveRoleMenus(Long roleId, Long[] menus);

    Set<Long> getRoleMenuIds(Long id);

    boolean deleteByMenuId(Long menuId);
}
