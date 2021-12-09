package com.godfunc.modules.sys.controller;


import com.godfunc.constant.LogRecordConstant;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.log.annotation.LogRecord;
import com.godfunc.modules.sys.dto.UserDTO;
import com.godfunc.modules.sys.dto.UserInfoDTO;
import com.godfunc.modules.sys.param.UserAddParam;
import com.godfunc.modules.sys.param.UserEditParam;
import com.godfunc.modules.sys.param.UserPasswordParam;
import com.godfunc.modules.sys.service.UserService;
import com.godfunc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@RestController
@LogRecord("用户")
@Api(tags = "用户管理")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("info")
    @ApiOperation("用户信息")
    public R<UserInfoDTO> info() {
        return R.ok(userService.getUserInfo());
    }


    @GetMapping("page/{page}/{limit}")
    @LogRecord(LogRecordConstant.PAGE)
    @ApiOperation(LogRecordConstant.PAGE)
    @PreAuthorize("hasAuthority('mg:user:page')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "path", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "path", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "query", dataType = "String", dataTypeClass = String.class),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", dataType = "int", dataTypeClass = Integer.class)
    })
    public R<PageDTO<UserDTO>> page(@PathVariable Integer page, @PathVariable Integer limit,
                                    @RequestParam(required = false) String username,
                                    @RequestParam(required = false) Integer status) {
        return R.ok(userService.getPage(page, limit, status, username));
    }

    @PostMapping("add")
    @LogRecord(LogRecordConstant.ADD)
    @ApiOperation(LogRecordConstant.ADD)
    @PreAuthorize("hasAuthority('mg:user:add')")
    public R<Long> add(@Valid @RequestBody UserAddParam param) {
        return R.ok(userService.add(param));
    }

    @PostMapping("edit")
    @LogRecord(LogRecordConstant.EDIT)
    @ApiOperation(LogRecordConstant.EDIT)
    @PreAuthorize("hasAuthority('mg:user:edit')")
    public R<Long> edit(@Valid @RequestBody UserEditParam param) {
        return R.ok(userService.edit(param));
    }


    @PostMapping("password")
    @LogRecord("修改密码")
    @ApiOperation("修改密码")
    public R<Boolean> password(@Valid @RequestBody UserPasswordParam param) {
        return R.ok(userService.password(param));
    }

    @PostMapping("remove/{id}")
    @LogRecord(LogRecordConstant.REMOVE)
    @ApiOperation(LogRecordConstant.REMOVE)
    @PreAuthorize("hasAuthority('mg:user:remove')")
    @ApiImplicitParam(name = "id", value = "用户id", paramType = "path", required = true, dataType = "Long", dataTypeClass = Long.class)
    public R<Boolean> remove(@PathVariable Long id) {
        return R.ok(userService.removeData(id));
    }

}
