package com.godfunc.modules.sys.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("修改配置")
public class ConfigEditParam {

    @ApiModelProperty("id")
    @NotNull(message = "请选择要修改的数据")
    private Long id;

    @ApiModelProperty("名称")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty("值")
    @NotNull(message = "值不能为空")
    private String value;

    @ApiModelProperty("备注")
    private String remark;
}
