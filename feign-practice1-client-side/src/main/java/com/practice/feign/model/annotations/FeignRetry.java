package com.practice.feign.model.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignRetry {
    
    int retry() default 1;
    long interval() default 1000;
    // Class<? extends Throwable>[] include() default {};

}
