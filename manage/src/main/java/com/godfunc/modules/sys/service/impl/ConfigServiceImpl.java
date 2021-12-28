package com.godfunc.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.sys.dto.ConfigDTO;
import com.godfunc.modules.sys.entity.Config;
import com.godfunc.modules.sys.mapper.ConfigMapper;
import com.godfunc.modules.sys.param.ConfigAddParam;
import com.godfunc.modules.sys.param.ConfigEditParam;
import com.godfunc.modules.sys.service.ConfigService;
import com.godfunc.util.Assert;
import com.godfunc.util.ConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 配置表 服务实现类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

    @Override
    public PageDTO<ConfigDTO> getPage(Long page, Long limit, String name) {
        IPage<ConfigDTO> resultPage = new Page<>(page, limit);
        List<Config> list = this.baseMapper.selectCustomPage(resultPage, name);
        resultPage.setRecords(ConvertUtils.source2Target(list, ConfigDTO.class));
        return new PageDTO<ConfigDTO>(resultPage);
    }

    @Override
    public Long add(ConfigAddParam param) {
        Config config = getOne(Wrappers.<Config>lambdaQuery().eq(Config::getName, param.getName()));
        Assert.isNotNull(config, "配置名称[{}]已存在", param.getName());
        config = new Config();
        BeanUtils.copyProperties(param, config);
        save(config);
        return config.getId();
    }

    @Override
    public Long edit(ConfigEditParam param) {
        Config config = getOne(Wrappers.<Config>lambdaQuery().ne(Config::getId, param.getId()).eq(Config::getName, param.getName()));
        Assert.isNotNull(config, "配置名称[{}]已存在", param.getName());
        config = getById(param.getId());
        Assert.isNull(config, "当前配置已不存");
        config.setName(param.getName());
        config.setValue(param.getValue());
        config.setRemark(param.getRemark());
        updateById(config);
        return config.getId();
    }
}
