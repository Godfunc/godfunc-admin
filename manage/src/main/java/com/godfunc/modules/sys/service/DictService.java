package com.godfunc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.sys.dto.DictDTO;
import com.godfunc.modules.sys.entity.Dict;
import com.godfunc.modules.sys.param.DictAddParam;
import com.godfunc.modules.sys.param.DictEditParam;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
public interface DictService extends IService<Dict> {

    PageDTO<DictDTO> getPage(Long page, Long limit, String name);

    Long add(DictAddParam param);

    Long edit(DictEditParam param);

}
