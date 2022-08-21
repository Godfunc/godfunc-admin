package com.godfunc.modules.log.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志表
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mg_log_operation")
public class LogOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求时长(毫秒)
     */
    private Integer requestTime;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 状态 0失败 1成功
     */
    private Integer status;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createId;

    /**
     * 用户名
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
