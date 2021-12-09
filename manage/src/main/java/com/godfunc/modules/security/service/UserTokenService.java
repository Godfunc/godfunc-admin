package com.godfunc.modules.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.modules.security.entity.UserToken;

/**
 * <p>
 * 用户token表 服务类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface UserTokenService extends IService<UserToken> {

    /**
     * 创建token
     *
     * @param userId 用户id
     * @return 返回创建的token信息
     */
    UserToken createToken(Long userId);

    /**
     * 根据token查询token数据
     *
     * @param token
     * @return
     */
    UserToken getByToken(String token);


    UserToken deleteByUserId(Long id);

    UserToken plusExpire(UserToken tokenEntity);
}
