package com.godfunc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.sys.dto.ConfigDTO;
import com.godfunc.modules.sys.entity.Config;
import com.godfunc.modules.sys.param.ConfigAddParam;
import com.godfunc.modules.sys.param.ConfigEditParam;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
public interface ConfigService extends IService<Config> {

    PageDTO<ConfigDTO> getPage(Long page, Long limit, String name);

    Long add(ConfigAddParam param);

    Long edit(ConfigEditParam param);

}
