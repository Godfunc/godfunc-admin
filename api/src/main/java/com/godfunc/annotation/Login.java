package com.godfunc.annotation;

import java.lang.annotation.*;

/**
 * 登录效验
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
