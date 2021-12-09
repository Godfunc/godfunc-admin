package com.godfunc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.modules.sys.dto.MenuDTO;
import com.godfunc.modules.sys.dto.MenuListDTO;
import com.godfunc.modules.sys.dto.MenuTreeDTO;
import com.godfunc.modules.sys.entity.Menu;
import com.godfunc.modules.sys.param.MenuAddParam;
import com.godfunc.modules.sys.param.MenuEditParam;

import java.util.List;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
public interface MenuService extends IService<Menu> {

    List<Menu> listOrderBySort();

    List<MenuListDTO> getList();

    Long add(MenuAddParam param);

    List<MenuDTO> getAll();

    boolean removeData(Long id);

    Long edit(MenuEditParam param);

    List<MenuTreeDTO> getTree();
}
