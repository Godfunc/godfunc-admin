package com.godfunc.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.godfunc.constant.ApiConstant;
import com.godfunc.dto.LoginDTO;
import com.godfunc.entity.UserToken;
import com.godfunc.mapper.UserTokenMapper;
import com.godfunc.service.UserTokenService;
import com.godfunc.util.TokenGenerateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {
    @Override
    public UserToken getByToken(String token) {
        return getOne(Wrappers.<UserToken>lambdaQuery().eq(UserToken::getToken, token));
    }

    @Override
    public boolean deleteByUserId(Long userId) {
        return remove(Wrappers.<UserToken>lambdaQuery().eq(UserToken::getUserId, userId));
    }

    @Override
    public LoginDTO createToken(Long userId) {
        UserToken userToken = getByUserId(userId);
        if (userToken == null) {
            userToken = new UserToken(userId, TokenGenerateUtils.getToken(), LocalDateTime.now().plusSeconds(ApiConstant.TOKEN_EXPIRED));
            save(userToken);
        } else {
            userToken.setExpireTime(LocalDateTime.now().plusSeconds(ApiConstant.TOKEN_EXPIRED));
            updateById(userToken);
        }
        return new LoginDTO(userToken.getToken(), userToken.getExpireTime());
    }

    @Override
    public UserToken getByUserId(Long userId) {
        return getOne(Wrappers.<UserToken>lambdaQuery().eq(UserToken::getUserId, userId));
    }

    @Override
    public UserToken updateExpired(UserToken userToken) {
        userToken.setExpireTime(LocalDateTime.now().plusSeconds(ApiConstant.TOKEN_EXPIRED));
        updateById(userToken);
        return userToken;
    }
}
