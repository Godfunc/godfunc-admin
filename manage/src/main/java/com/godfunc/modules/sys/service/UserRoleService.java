package com.godfunc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.modules.sys.entity.UserRole;

import java.util.List;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
public interface UserRoleService extends IService<UserRole> {

    List<UserRole> getUserRoles(Long userId);

    void saveRoles(Long userId, Long[] roles);

    boolean removeByUserId(Long userId);
}
