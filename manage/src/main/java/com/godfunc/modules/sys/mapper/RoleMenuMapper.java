package com.godfunc.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godfunc.modules.sys.entity.RoleMenu;
import com.godfunc.modules.sys.model.RoleMenuModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<RoleMenuModel> selectRoleMenu(@Param("roles") Set<Long> roles);
    List<RoleMenuModel> selectAllRoleMenu();
}
