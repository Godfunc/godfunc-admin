package com.godfunc.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godfunc.modules.sys.entity.UserRole;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRole> selectUserRoles(Long userId);
}
