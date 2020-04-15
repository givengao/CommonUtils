package com.zxyun.common.db.mysql.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TableField {
    String value() default "";

    boolean exist() default true;

    String condition() default "";

    String update() default "";
}
