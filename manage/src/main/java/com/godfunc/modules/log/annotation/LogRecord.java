package com.godfunc.modules.log.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * @author godfunc
 * @email godfunc@outlook.com
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {

    String value() default "";
}
