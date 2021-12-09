package com.godfunc.modules.log.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.log.dto.LogDTO;
import com.godfunc.modules.log.entity.LogOperation;
import com.godfunc.modules.log.mapper.LogOperationMapper;
import com.godfunc.modules.log.service.LogOperationService;
import com.godfunc.util.ConvertUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@Service
public class LogOperationServiceImpl extends ServiceImpl<LogOperationMapper, LogOperation> implements LogOperationService {

    @Override
    public PageDTO<LogDTO> getPage(Integer page, Integer limit, Integer status, String operation) {
        IPage<LogDTO> resultPage = new Page<>(page, limit);
        List<LogOperation> list = this.baseMapper.selectCustomPage(resultPage, status, operation);
        resultPage.setRecords(ConvertUtils.source2Target(list, LogDTO.class));
        return new PageDTO<LogDTO>(resultPage);
    }
}
