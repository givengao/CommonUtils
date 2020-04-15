package com.zxyun.common.db.mysql.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TableName {
    String value() default "";

    String schema() default "";

    boolean keepGlobalPrefix() default false;

    String resultMap() default "";

    boolean autoResultMap() default false;
}
