package com.godfunc.modules.log.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.log.dto.LogDTO;
import com.godfunc.modules.log.entity.LogOperation;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface LogOperationService extends IService<LogOperation> {

    PageDTO<LogDTO> getPage(Integer page, Integer limit, Integer status, String operation);
}
