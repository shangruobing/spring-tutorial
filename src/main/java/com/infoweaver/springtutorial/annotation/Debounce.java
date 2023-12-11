package com.infoweaver.springtutorial.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ruobing Shang 2023-11-07 17:30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Debounce {
    /**
     * 设置默认的防抖时间间隔，单位为毫秒
     */
    int value() default 1000;
}
