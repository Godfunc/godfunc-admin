package com.godfunc.modules.sys.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.godfunc.model.TreeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("菜单树")
public class MenuTreeDTO extends TreeModel<MenuTreeDTO> implements Serializable {

    @ApiModelProperty("菜单id")
    private Long id;

    @JsonIgnore
    private Long pid;

    @ApiModelProperty("菜单名")
    private String name;

    @ApiModelProperty("子菜单")
    private List<MenuTreeDTO> children = new ArrayList<>();
}
