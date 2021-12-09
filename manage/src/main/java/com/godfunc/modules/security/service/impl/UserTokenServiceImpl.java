package com.godfunc.modules.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.godfunc.common.constant.ManageConstant;
import com.godfunc.modules.security.entity.UserToken;
import com.godfunc.modules.security.mapper.UserTokenMapper;
import com.godfunc.modules.security.service.UserTokenService;
import com.godfunc.util.TokenGenerateUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户token表 服务实现类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@Service
@CacheConfig(cacheNames = "userToken")
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserToken> implements UserTokenService {

    @Override
    public UserToken createToken(Long userId) {
        UserToken userToken = getOne(Wrappers.<UserToken>lambdaQuery().eq(UserToken::getUserId, userId));
        if (userToken == null) {
            String token = TokenGenerateUtils.getToken();
            userToken = new UserToken(userId, token, LocalDateTime.now().plusSeconds(ManageConstant.TOKEN_EXPIRED));
            save(userToken);
        } else if (userToken.getExpireTime().isBefore(LocalDateTime.now())) {
            String token = TokenGenerateUtils.getToken();
            userToken.setExpireTime(LocalDateTime.now().plusSeconds(ManageConstant.TOKEN_EXPIRED));
            userToken.setToken(token);
            updateById(userToken);
        }
        return userToken;
    }

    @Override
    @Cacheable(unless = "#result == null", key = "#token")
    public UserToken getByToken(String token) {
        return getOne(Wrappers.<UserToken>lambdaQuery().eq(UserToken::getToken, token));
    }

    @Override
    @CacheEvict(key = "#result.token")
    public UserToken deleteByUserId(Long userId) {
        UserToken usertoken = getOne(Wrappers.<UserToken>lambdaQuery().eq(UserToken::getUserId, userId));
        remove(Wrappers.<UserToken>lambdaQuery().eq(UserToken::getUserId, userId));
        return usertoken;
    }

    @Override
    @CachePut(key = "#result.token")
    public UserToken plusExpire(UserToken tokenEntity) {
        tokenEntity.setExpireTime(LocalDateTime.now().plusSeconds(ManageConstant.TOKEN_EXPIRED));
        updateById(tokenEntity);
        return tokenEntity;
    }
}
