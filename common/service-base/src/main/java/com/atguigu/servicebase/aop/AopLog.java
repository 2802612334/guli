package com.atguigu.servicebase.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Slf4j
@Component
public class AopLog {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut(value = "execution(* com.atguigu..controller.*.*(..))")
    private void aopWebLog(){

    }

    @Before(value = "aopWebLog()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        // 接受到请求，记录请求内容
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 记录请求内容
        log.info("URL：" + request.getRequestURL());
        log.info("HTTP方法：" + request.getMethod());
        log.info("IP地址：" + request.getRemoteAddr());
        log.info("类的方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." +joinPoint.getSignature().getName());
        log.info("参数：" + request.getQueryString());
    }

    @AfterReturning(value = "aopWebLog()",returning = "retObject")
    public void doAfterReturning(Object retObject){
        log.info("应答值：" + retObject);
        log.info("耗时（ms）：" + (System.currentTimeMillis() - startTime.get()));
    }
}
