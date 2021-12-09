package com.godfunc.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.godfunc.modules.security.util.SecurityUser;
import com.godfunc.modules.sys.dto.MenuListDTO;
import com.godfunc.modules.sys.dto.MenuDTO;
import com.godfunc.modules.sys.dto.MenuTreeDTO;
import com.godfunc.modules.sys.entity.Menu;
import com.godfunc.modules.sys.enums.MenuTypeEnum;
import com.godfunc.modules.sys.enums.SuperManagerEnum;
import com.godfunc.modules.sys.mapper.MenuMapper;
import com.godfunc.modules.sys.model.UserDetail;
import com.godfunc.modules.sys.param.MenuAddParam;
import com.godfunc.modules.sys.param.MenuEditParam;
import com.godfunc.modules.sys.service.MenuService;
import com.godfunc.modules.sys.service.RoleMenuService;
import com.godfunc.util.Assert;
import com.godfunc.util.ConvertUtils;
import com.godfunc.util.TreeUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final RoleMenuService roleMenuService;

    @Override
    public List<MenuDTO> getAll() {
        List<Menu> menus = listOrderBySort();
        if (CollectionUtils.isEmpty(menus)) {
            return null;
        } else {
            List<MenuDTO> list = ConvertUtils.source2Target(menus, MenuDTO.class);
            return TreeUtils.build(list);
        }
    }

    @Override
    public List<MenuListDTO> getList() {
        UserDetail user = SecurityUser.getUser();
        List<Menu> menus = null;
        if (SuperManagerEnum.SUPER_MANAGER.getValue() == user.getSuperManager()) {
            menus = this.baseMapper.selectEnable(MenuTypeEnum.MENU.getValue());
        } else {
            if (CollectionUtils.isNotEmpty(user.getRoles())) {
                menus = this.baseMapper.selectUserMenu(user.getRoles(), MenuTypeEnum.MENU.getValue());
            }
        }
        List<MenuListDTO> list = ConvertUtils.source2Target(menus, MenuListDTO.class);
        return TreeUtils.build(list);
    }

    @Override
    public List<MenuTreeDTO> getTree() {
        List<Menu> menus = listOrderBySort();
        if (CollectionUtils.isEmpty(menus)) {
            return null;
        } else {
            List<MenuTreeDTO> list = ConvertUtils.source2Target(menus, MenuTreeDTO.class);
            return TreeUtils.build(list);
        }
    }

    @Override
    public List<Menu> listOrderBySort() {
        return list(Wrappers.<Menu>lambdaQuery().orderByAsc(Menu::getSort));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "user::detail", allEntries = true)
    public Long add(MenuAddParam param) {
        if (MenuTypeEnum.MENU.getValue() == param.getType()) {
            Assert.isBlank(param.getComponent(), "组件不能为空");
            Assert.isBlank(param.getPath(), "路由不能为空");
        } else {
            Assert.isBlank(param.getName(), "名称不能为空");
            Assert.isBlank(param.getComponent(), "组件不能为空");
            Assert.isBlank(param.getPermissions(), "名称不能为空");
            Assert.isNull(param.getPid(), "父菜单不能为空");
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(param, menu);
        save(menu);
        return menu.getId();
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "user::detail", allEntries = true)
    public Long edit(MenuEditParam param) {
        if (MenuTypeEnum.MENU.getValue() == param.getType()) {
            Assert.isBlank(param.getComponent(), "组件不能为空");
            Assert.isBlank(param.getPath(), "路由不能为空");
        } else {
            Assert.isBlank(param.getName(), "名称不能为空");
            Assert.isBlank(param.getComponent(), "组件不能为空");
            Assert.isBlank(param.getPermissions(), "名称不能为空");
            Assert.isNull(param.getPid(), "父菜单不能为空");
        }
        Menu menu = getById(param.getId());
        Assert.isNull(menu, "您选择的菜单不存在");
        BeanUtils.copyProperties(param, menu);
        updateById(menu);
        return menu.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "user::detail", allEntries = true)
    public boolean removeData(Long id) {
        deleteData(id);
        return true;
    }

    private void deleteData(Long id) {
        removeById(id);
        roleMenuService.deleteByMenuId(id);
        List<Menu> list = list(Wrappers.<Menu>lambdaQuery().eq(Menu::getPid, id));
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(x -> deleteData(x.getId()));
        }
    }
}
