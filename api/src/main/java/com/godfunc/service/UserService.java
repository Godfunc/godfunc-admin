package com.godfunc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.dto.LoginDTO;
import com.godfunc.entity.User;
import com.godfunc.param.LoginParam;
import com.godfunc.param.RegisterParam;

/**
 * @author Godfunc
 * @email godfunc@outlook.com
 */
public interface UserService extends IService<User> {
    LoginDTO login(LoginParam user);

    User getByMobile(String mobile);

    boolean logout(Long userId);

    LoginDTO register(RegisterParam param);
}
