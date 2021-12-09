package com.godfunc.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author godfunc
 */
@Data
@ApiModel("用户列表")
public class UserDTO implements Serializable {

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("性别 1男 2女 3未知")
    private Integer gender;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("超级管理员 0不是 1是")
    private Integer superManager;

    @ApiModelProperty("状态 0停用 1启用")
    private Integer status;

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
