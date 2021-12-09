package com.godfunc.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.godfunc.modules.sys.entity.UserRole;
import com.godfunc.modules.sys.mapper.UserRoleMapper;
import com.godfunc.modules.sys.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<UserRole> getUserRoles(Long userId) {
        return this.baseMapper.selectUserRoles(userId);
    }

    @Override
    public void saveRoles(Long userId, Long[] roles) {
        if (roles != null && roles.length > 0) {
            List<UserRole> list = new ArrayList<>();
            for (Long role : roles) {
                UserRole userRole = new UserRole(userId, role);
                list.add(userRole);
            }
            saveBatch(list);
        }
    }

    @Override
    public boolean removeByUserId(Long userId) {
        return remove(Wrappers.<UserRole>lambdaQuery().eq(UserRole::getUserId, userId));
    }
}
