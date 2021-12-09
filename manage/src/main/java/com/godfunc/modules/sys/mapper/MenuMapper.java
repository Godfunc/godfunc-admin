package com.godfunc.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.godfunc.modules.sys.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> selectUserMenu(@Param("roles") Set<String> roles, @Param("type") Integer type);

    List<Menu> selectEnable(Integer type);
}
