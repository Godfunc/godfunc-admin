package com.godfunc.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.sys.dto.RoleDTO;
import com.godfunc.modules.sys.dto.RoleListDTO;
import com.godfunc.modules.sys.entity.Role;
import com.godfunc.modules.sys.entity.RoleMenu;
import com.godfunc.modules.sys.entity.UserRole;
import com.godfunc.modules.sys.mapper.RoleMapper;
import com.godfunc.modules.sys.param.RoleAddParam;
import com.godfunc.modules.sys.param.RoleEditParam;
import com.godfunc.modules.sys.service.RoleMenuService;
import com.godfunc.modules.sys.service.RoleService;
import com.godfunc.modules.sys.service.UserRoleService;
import com.godfunc.util.Assert;
import com.godfunc.util.ConvertUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMenuService roleMenuService;
    private final UserRoleService userRoleService;

    @Override
    public PageDTO<RoleDTO> getPage(Long page, Long limit, String name) {
        IPage<RoleDTO> resultPage = new Page<>(page, limit);
        List<Role> list = this.baseMapper.selectCustomPage(resultPage, name);
        resultPage.setRecords(ConvertUtils.source2Target(list, RoleDTO.class));
        return new PageDTO<RoleDTO>(resultPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(RoleAddParam param) {
        Role role = getOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, param.getName()));
        Assert.isNotNull(role, "角色名[{}]已存在", param.getName());
        role = new Role();
        BeanUtils.copyProperties(param, role);
        save(role);
        roleMenuService.saveRoleMenus(role.getId(), param.getMenus());
        return role.getId();
    }

    @Override
    @CacheEvict(cacheNames = "user::detail", allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public Long edit(RoleEditParam param) {
        Role role = getOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, param.getName()).ne(Role::getId, param.getId()));
        Assert.isNotNull(role, "角色名[{}]已存在", param.getName());
        role = getById(param.getId());
        Assert.isNull(role, "当前修改的角色已不存");
        role.setName(param.getName());
        role.setRemark(param.getRemark());
        updateById(role);
        roleMenuService.remove(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, role.getId()));
        roleMenuService.saveRoleMenus(role.getId(), param.getMenus());
        return role.getId();
    }

    @Override
    @CacheEvict(cacheNames = "user::detail", allEntries = true)
    public boolean removeDate(Long id) {
        boolean b = removeById(id);
        roleMenuService.remove(Wrappers.<RoleMenu>lambdaQuery().eq(RoleMenu::getRoleId, id));
        userRoleService.remove(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getRoleId, id));
        return b;
    }

    @Override
    public Set<Long> getMenus(Long id) {
        return roleMenuService.getRoleMenuIds(id);
    }

    @Override
    public List<RoleListDTO> getList() {
        List<Role> list = list();
        return ConvertUtils.source2Target(list, RoleListDTO.class);
    }

    @Override
    public Set<Long> getByUser(Long userId) {
        List<UserRole> list = userRoleService.list(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        }
    }
}
