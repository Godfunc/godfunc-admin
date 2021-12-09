package com.godfunc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.sys.dto.RoleDTO;
import com.godfunc.modules.sys.dto.RoleListDTO;
import com.godfunc.modules.sys.entity.Role;
import com.godfunc.modules.sys.param.RoleAddParam;
import com.godfunc.modules.sys.param.RoleEditParam;
import com.godfunc.result.R;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface RoleService extends IService<Role> {

    PageDTO<RoleDTO> getPage(Long page, Long limit, String name);

    Long add(RoleAddParam param);

    Long edit(RoleEditParam param);

    boolean removeDate(Long id);

    Set<Long> getMenus(Long id);

    List<RoleListDTO> getList();

    Set<Long> getByUser(Long userId);
}
