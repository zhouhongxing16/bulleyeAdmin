package com.chris.bulleyeadmin.common.config;

import java.lang.annotation.*;

/**
 * @author Chris
 * @date 2022-01-06 14:45
 */
@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    int limit() default 5;
    int sec() default 5;
}
