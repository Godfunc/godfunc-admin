package com.godfunc.modules.security.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户token表
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mg_user_token")
@NoArgsConstructor
public class UserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    public UserToken(Long userId, String token, LocalDateTime expireTime) {
        this.userId = userId;
        this.token = token;
        this.expireTime = expireTime;
        this.createTime = LocalDateTime.now();
    }

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
