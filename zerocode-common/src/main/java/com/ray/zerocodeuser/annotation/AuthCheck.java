package com.ray.zerocodeuser.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验
 */
@Target(ElementType.METHOD) //生效目标
@Retention(RetentionPolicy.RUNTIME) //注解策略
public @interface AuthCheck {

    /**
     * 必须有某个角色
     */
    String mustRole() default "";
}
