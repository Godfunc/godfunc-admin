package com.godfunc.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.godfunc.modules.sys.entity.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface DictMapper extends BaseMapper<Dict> {

    List<Dict> selectCustomPage(IPage resultPage, @Param("name") String name);
}
