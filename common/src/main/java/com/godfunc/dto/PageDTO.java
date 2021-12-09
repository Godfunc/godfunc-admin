package com.godfunc.dto;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Godfunc
 * @date 2019/12/10 21:45
 */
@Data
@ApiModel("分页")
public class PageDTO<T> implements Serializable {

    @ApiModelProperty("总条数")
    private int total;

    @ApiModelProperty("列表数据")
    private List<T> list;

    public PageDTO(IPage<T> page) {
        this.list = page.getRecords();
        this.total = (int) page.getTotal();
    }
}
