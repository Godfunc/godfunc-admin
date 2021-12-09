package com.godfunc.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@ApiModel("角色分页")
public class RoleDTO implements Serializable {

    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty("角色名")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建人id")
    private Long createId;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("更新人id")
    private Long updateId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
