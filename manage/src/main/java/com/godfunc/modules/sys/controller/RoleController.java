package com.godfunc.modules.sys.controller;


import com.godfunc.constant.LogRecordConstant;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.log.annotation.LogRecord;
import com.godfunc.modules.sys.dto.RoleDTO;
import com.godfunc.modules.sys.dto.RoleListDTO;
import com.godfunc.modules.sys.param.RoleAddParam;
import com.godfunc.modules.sys.param.RoleEditParam;
import com.godfunc.modules.sys.service.RoleService;
import com.godfunc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@RestController
@LogRecord("角色")
@Api(tags = "角色")
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;


    @GetMapping("page/{page}/{limit}")
    @LogRecord(LogRecordConstant.PAGE)
    @ApiOperation(LogRecordConstant.PAGE)
    @PreAuthorize("hasAuthority('mg:role:page')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "path", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "path", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "name", value = "角色名", paramType = "query", dataType = "String", dataTypeClass = String.class)
    })
    public R<PageDTO<RoleDTO>> page(@PathVariable Long page, @PathVariable Long limit, @RequestParam(required = false) String name) {
        return R.ok(roleService.getPage(page, limit, name));
    }

    @GetMapping("list")
    @LogRecord(LogRecordConstant.LIST)
    @ApiOperation(LogRecordConstant.LIST)
    @PreAuthorize("hasAuthority('mg:role:list')")
    public R<List<RoleListDTO>> list() {
        return R.ok(roleService.getList());
    }

    @GetMapping("getByUser/{userId}")
    @LogRecord("用户角色")
    @ApiOperation("查询用户角色")
    @PreAuthorize("hasAuthority('mg:role:getByUser')")
    @ApiImplicitParam(name = "userId", value = "用户id", paramType = "path", required = true, dataType = "Long", dataTypeClass = Long.class)
    public R<Set<Long>> getByUser(@PathVariable Long userId) {
        return R.ok(roleService.getByUser(userId));
    }

    @PostMapping("add")
    @LogRecord(LogRecordConstant.ADD)
    @ApiOperation(LogRecordConstant.ADD)
    @PreAuthorize("hasAuthority('mg:role:add')")
    public R<Long> add(@Valid @RequestBody RoleAddParam param) {
        return R.ok(roleService.add(param));
    }

    @PostMapping("edit")
    @LogRecord(LogRecordConstant.EDIT)
    @ApiOperation(LogRecordConstant.EDIT)
    @PreAuthorize("hasAuthority('mg:role:edit')")
    public R<Long> edit(@Valid @RequestBody RoleEditParam param) {
        return R.ok(roleService.edit(param));
    }

    @PostMapping("remove/{id}")
    @LogRecord(LogRecordConstant.REMOVE)
    @ApiOperation(LogRecordConstant.REMOVE)
    @PreAuthorize("hasAuthority('mg:role:remove')")
    @ApiImplicitParam(name = "id", value = "角色id", paramType = "path", required = true, dataType = "Long", dataTypeClass = Long.class)
    public R<Boolean> remove(@PathVariable Long id) {
        return R.ok(roleService.removeDate(id));
    }

    @GetMapping("getMenus/{id}")
    @LogRecord("菜单")
    @ApiOperation("查询角色的菜单")
    @PreAuthorize("hasAuthority('mg:role:getMenus')")
    @ApiImplicitParam(name = "id", value = "角色id", paramType = "path", required = true, dataType = "Long", dataTypeClass = Long.class)
    public R<Set<Long>> getMenus(@PathVariable Long id) {
        return R.ok(roleService.getMenus(id));
    }
}
