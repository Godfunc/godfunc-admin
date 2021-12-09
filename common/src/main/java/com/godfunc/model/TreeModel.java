package com.godfunc.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author godfunc
 * @email godfunc@outlook.com
 * @param <T>
 */
@Data
public class TreeModel<T> implements Serializable {

    private Long id;

    private Long pid;

    private List<T> children = new ArrayList<>();
}
