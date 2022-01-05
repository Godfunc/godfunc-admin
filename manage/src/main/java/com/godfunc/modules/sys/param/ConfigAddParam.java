package com.godfunc.modules.sys.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("新增配置")
public class ConfigAddParam {

    @ApiModelProperty("名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty("值")
    @NotBlank(message = "值不能为空")
    private String value;

    @ApiModelProperty("备注")
    private String remark;
}
