package com.godfunc.modules.log.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Godfunc
 * @date 2019/12/10 21:45
 */
@Data
@ApiModel("日志信息")
public class LogDTO implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户操作")
    private String operation;

    @ApiModelProperty("请求地址")
    private String requestUrl;

    @ApiModelProperty("请求参数")
    private String requestParams;

    @ApiModelProperty("请求时长(毫秒)")
    private Integer requestTime;

    @ApiModelProperty("用户代理")
    private String userAgent;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("状态 0失败 1成功")
    private Integer status;

    @ApiModelProperty("用户名")
    private String createUser;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
