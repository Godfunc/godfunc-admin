package com.godfunc.modules.sys.controller;


import com.godfunc.constant.LogRecordConstant;
import com.godfunc.dto.PageDTO;
import com.godfunc.modules.log.annotation.LogRecord;
import com.godfunc.modules.sys.dto.DictDTO;
import com.godfunc.modules.sys.param.DictAddParam;
import com.godfunc.modules.sys.param.DictEditParam;
import com.godfunc.modules.sys.service.DictService;
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
 * 字典表 前端控制器
 * </p>
 *
 * @author Godfunc
 * @since 2019-12-01
 */
@RestController
@LogRecord("字典")
@Api(tags = "字典")
@RequestMapping("/dict")
@RequiredArgsConstructor
public class DictController {

    private final DictService dictService;


    @GetMapping("page/{page}/{limit}")
    @LogRecord(LogRecordConstant.PAGE)
    @ApiOperation(LogRecordConstant.PAGE)
    @PreAuthorize("hasAuthority('mg:dict:page')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", paramType = "path", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "path", required = true, dataType = "int", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "name", value = "名称", paramType = "query", dataType = "String", dataTypeClass = String.class)
    })
    public R<PageDTO<DictDTO>> page(@PathVariable Long page, @PathVariable Long limit, @RequestParam(required = false) String name) {
        return R.ok(dictService.getPage(page, limit, name));
    }


    @PostMapping("add")
    @LogRecord(LogRecordConstant.ADD)
    @ApiOperation(LogRecordConstant.ADD)
    @PreAuthorize("hasAuthority('mg:dict:add')")
    public R<Long> add(@Valid @RequestBody DictAddParam param) {
        return R.ok(dictService.add(param));
    }

    @PostMapping("edit")
    @LogRecord(LogRecordConstant.EDIT)
    @ApiOperation(LogRecordConstant.EDIT)
    @PreAuthorize("hasAuthority('mg:dict:edit')")
    public R<Long> edit(@Valid @RequestBody DictEditParam param) {
        return R.ok(dictService.edit(param));
    }

    @PostMapping("remove/{id}")
    @LogRecord(LogRecordConstant.REMOVE)
    @ApiOperation(LogRecordConstant.REMOVE)
    @PreAuthorize("hasAuthority('mg:dict:remove')")
    @ApiImplicitParam(name = "id", value = "字典id", paramType = "path", required = true, dataType = "Long", dataTypeClass = Long.class)
    public R<Boolean> remove(@PathVariable Long id) {
        return R.ok(dictService.removeById(id));
    }
}
