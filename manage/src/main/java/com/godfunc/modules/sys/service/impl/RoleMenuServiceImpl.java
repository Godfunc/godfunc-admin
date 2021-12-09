package com.godfunc.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.godfunc.modules.sys.entity.RoleMenu;
import com.godfunc.modules.sys.mapper.RoleMenuMapper;
import com.godfunc.modules.sys.model.RoleMenuModel;
import com.godfunc.modules.sys.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public List<RoleMenuModel> getRoleMenu(Set<Long> roles) {
        return this.baseMapper.selectRoleMenu(roles);
    }

    @Override
    public List<RoleMenuModel> getAllRoleMenu() {
        return this.baseMapper.selectAllRoleMenu();
    }

    @Override
    public void saveRoleMenus(Long roleId, Long[] menus) {
        if (menus != null && menus.length > 0) {
            List<RoleMenu> roleMenus = new ArrayList<>();
            for (Long menu : menus) {
                RoleMenu roleMenu = new RoleMenu(roleId, menu);
                roleMenus.add(roleMenu);
            }
            saveBatch(roleMenus);
        }
    }

    @Override
    public Set<Long> getRoleMenuIds(Long id) {
        List<RoleMenu> list = list(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, id));
        return list.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
    }

    @Override
    public boolean deleteByMenuId(Long menuId) {
        return remove(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getMenuId, menuId));
    }
}
