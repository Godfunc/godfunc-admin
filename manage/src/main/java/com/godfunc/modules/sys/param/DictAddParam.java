package com.godfunc.modules.sys.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("新增字典")
public class DictAddParam {

    @ApiModelProperty("名称")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty("值")
    @NotNull(message = "值不能为空")
    private String value;

    @ApiModelProperty("备注")
    private String remark;
}
