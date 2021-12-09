package com.godfunc.modules.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.godfunc.modules.log.entity.LogOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 操作日志表 Mapper 接口
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface LogOperationMapper extends BaseMapper<LogOperation> {

    List<LogOperation> selectCustomPage(IPage page, @Param("status") Integer status, @Param("operation") String operation);
}
