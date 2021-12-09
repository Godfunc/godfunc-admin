package com.godfunc.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.godfunc.modules.sys.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> selectCustomPage(IPage page, @Param("name") String name);
}
