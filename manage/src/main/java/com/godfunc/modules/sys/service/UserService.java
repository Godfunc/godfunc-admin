package com.godfunc.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.sys.dto.UserDTO;
import com.godfunc.modules.sys.dto.UserInfoDTO;
import com.godfunc.modules.sys.entity.User;
import com.godfunc.modules.sys.param.UserAddParam;
import com.godfunc.modules.sys.param.UserEditParam;
import com.godfunc.modules.sys.param.UserPasswordParam;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface UserService extends IService<User>, UserDetailsService {

    User getByUsername(String username);

    UserDetails getByUserId(Long id);

    UserInfoDTO getUserInfo();

    PageDTO<UserDTO> getPage(Integer page, Integer limit, Integer status, String username);

    Long add(UserAddParam param);

    Long edit(UserEditParam param);

    boolean removeData(Long id);

    boolean password(UserPasswordParam param);

    void logout(Long id);
}
