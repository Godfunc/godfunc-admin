package com.godfunc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.dto.LoginDTO;
import com.godfunc.entity.UserToken;

/**
 * @author Godfunc
 * @email godfunc@outlook.com
 */
public interface UserTokenService extends IService<UserToken> {
    UserToken getByToken(String token);

    boolean deleteByUserId(Long userId);

    LoginDTO createToken(Long userId);

    UserToken getByUserId(Long userId);

    UserToken updateExpired(UserToken userToken);
}
