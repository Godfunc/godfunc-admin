package com.godfunc.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.godfunc.dto.LoginDTO;
import com.godfunc.entity.User;
import com.godfunc.enums.UserStatusEnum;
import com.godfunc.exception.GException;
import com.godfunc.mapper.UserMapper;
import com.godfunc.param.LoginParam;
import com.godfunc.param.RegisterParam;
import com.godfunc.result.ApiCode;
import com.godfunc.service.UserService;
import com.godfunc.service.UserTokenService;
import com.godfunc.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @author Godfunc
 * @email godfunc@outlook.com
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserTokenService userTokenService;

    @Override
    public LoginDTO login(LoginParam param) {
        User user = getByMobile(param.getMobile());
        Assert.isNull(user, "手机号{}不存在", param.getMobile());
        if (UserStatusEnum.DISABLE.value() == user.getStatus()) {
            throw new GException("当前账号已被停用", ApiCode.NO_PERMISSION);
        }
        Assert.isTrue(!PasswordUtils.matches(param.getPassword(), user.getPassword()), "密码不正确");
        return userTokenService.createToken(user.getId());
    }

    @Override
    public User getByMobile(String mobile) {
        return getOne(Wrappers.<User>lambdaQuery().eq(User::getMobile, mobile));
    }

    @Override
    public boolean logout(Long userId) {
        return userTokenService.deleteByUserId(userId);
    }

    @Override
    public LoginDTO register(RegisterParam param) {
        Assert.isTrue(!param.getPassword().equals(param.getConfirmPassword()), "两次输入密码不一样，请重新输入");
        User user = new User();
        user.setMobile(param.getMobile());
        user.setPassword(PasswordUtils.encode(param.getPassword()));
        user.setStatus(UserStatusEnum.ENABLE.value());
        user.setUsername(param.getUsername());
        save(user);
        return userTokenService.createToken(user.getId());
    }

}
