package com.practice.feign.aop.aspects;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.practice.feign.model.annotations.FeignRetry;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class TaskRestClient2Aspect {
    
    @Around("@annotation(com.practice.feign.model.annotations.FeignRetry)")
    public Object taskClientFeignRetry(ProceedingJoinPoint jp) throws Throwable {

        Method method = getMethod(jp);
        FeignRetry feignRetry = method.getAnnotation(FeignRetry.class);
        log.info(">> TaskRestClient2Aspect - taskClientFeignRetry() of {}", method.getName());
        Object result = retry(jp, feignRetry.retry(), feignRetry.interval());
        return result;
    } 

    private Object retry(ProceedingJoinPoint jp, int attempts, long interval) throws Throwable {
        Object result;
        try {
            result = jp.proceed(jp.getArgs());
            return result;
        } 
        catch (Exception exc) {
            log.info("calling again: attempts: {}", attempts);
            Thread.sleep(interval);
            if(attempts-- != 0) {
                return retry(jp, attempts, interval);
            }
            throw exc;
        }
    }

    private Method getMethod(ProceedingJoinPoint jp) {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        return methodSignature.getMethod();
    }
}
