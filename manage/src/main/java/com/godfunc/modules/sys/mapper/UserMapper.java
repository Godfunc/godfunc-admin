package com.godfunc.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.godfunc.modules.sys.dto.UserDTO;
import com.godfunc.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
public interface UserMapper extends BaseMapper<User> {

    List<UserDTO> selectCustomPage(IPage page, @Param("username") String username, @Param("status") Integer status, @Param("superManager") Integer superManager);

}
