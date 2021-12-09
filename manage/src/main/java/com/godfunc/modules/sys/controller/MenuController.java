package com.godfunc.modules.sys.controller;


import com.godfunc.constant.LogRecordConstant;
import com.godfunc.modules.log.annotation.LogRecord;
import com.godfunc.modules.sys.dto.MenuDTO;
import com.godfunc.modules.sys.dto.MenuListDTO;
import com.godfunc.modules.sys.dto.MenuTreeDTO;
import com.godfunc.modules.sys.param.MenuAddParam;
import com.godfunc.modules.sys.param.MenuEditParam;
import com.godfunc.modules.sys.service.MenuService;
import com.godfunc.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@RestController
@LogRecord("菜单")
@Api(tags = "菜单")
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("getAll")
    @LogRecord("管理")
    @ApiOperation("菜单列表")
    @PreAuthorize("hasAuthority('mg:menu:getAll')")
    public R<List<MenuDTO>> page() {
        return R.ok(menuService.getAll());
    }

    @GetMapping("getTree")
    @LogRecord("选项树")
    @ApiOperation("选项树")
    @PreAuthorize("hasAuthority('mg:menu:getTree')")
    public R<List<MenuTreeDTO>> getTree() {
        return R.ok(menuService.getTree());
    }

    @GetMapping("list")
    @LogRecord("获取菜单")
    @ApiOperation("获取菜单")
    public R<List<MenuListDTO>> list() {
        return R.ok(menuService.getList());
    }

    @PostMapping("add")
    @LogRecord(LogRecordConstant.ADD)
    @ApiOperation(LogRecordConstant.ADD)
    @PreAuthorize("hasAuthority('mg:menu:add')")
    public R<Long> add(@Valid @RequestBody MenuAddParam param) {
        return R.ok(menuService.add(param));
    }

    @PostMapping("edit")
    @LogRecord(LogRecordConstant.EDIT)
    @ApiOperation(LogRecordConstant.EDIT)
    @PreAuthorize("hasAuthority('mg:menu:edit')")
    public R<Long> edit(@Valid @RequestBody MenuEditParam param) {
        return R.ok(menuService.edit(param));
    }

    @PostMapping("remove/{id}")
    @LogRecord(LogRecordConstant.REMOVE)
    @ApiOperation(LogRecordConstant.REMOVE)
    @PreAuthorize("hasAuthority('mg:menu:remove')")
    @ApiImplicitParam(name = "id", value = "菜单id", paramType = "path", required = true, dataType = "Long", dataTypeClass = Long.class)
    public R<Boolean> remove(@PathVariable Long id) {
        return R.ok(menuService.removeData(id));
    }

}
