package com.godfunc.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.sys.dto.DictDTO;
import com.godfunc.modules.sys.entity.Dict;
import com.godfunc.modules.sys.mapper.DictMapper;
import com.godfunc.modules.sys.param.DictAddParam;
import com.godfunc.modules.sys.param.DictEditParam;
import com.godfunc.modules.sys.service.DictService;
import com.godfunc.util.Assert;
import com.godfunc.util.ConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public PageDTO<DictDTO> getPage(Long page, Long limit, String name) {
        IPage<DictDTO> resultPage = new Page<>(page, limit);
        List<Dict> list = this.baseMapper.selectCustomPage(resultPage, name);
        resultPage.setRecords(ConvertUtils.source2Target(list, DictDTO.class));
        return new PageDTO<DictDTO>(resultPage);
    }

    @Override
    public Long add(DictAddParam param) {
        Dict dict = getOne(Wrappers.<Dict>lambdaQuery().eq(Dict::getName, param.getName()));
        Assert.isNotNull(dict, "字典名称[{}]已存在", param.getName());
        dict = new Dict();
        BeanUtils.copyProperties(param, dict);
        save(dict);
        return dict.getId();
    }

    @Override
    public Long edit(DictEditParam param) {
        Dict dict = getOne(Wrappers.<Dict>lambdaQuery().ne(Dict::getId, param.getId()).eq(Dict::getName, param.getName()));
        Assert.isNotNull(dict, "字典名称[{}]已存在", param.getName());
        dict = getById(param.getId());
        Assert.isNull(dict, "当前字典项已不存");
        dict.setName(param.getName());
        dict.setValue(param.getValue());
        dict.setRemark(param.getRemark());
        updateById(dict);
        return dict.getId();
    }
}
