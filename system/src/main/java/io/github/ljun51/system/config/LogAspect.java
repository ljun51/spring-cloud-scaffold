package io.github.ljun51.system.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private final static Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * io.github.ljun51.*.web.*Controller.*(..))")
    public void aspectPoint() {
    }

    @Before("aspectPoint()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            // 开始打印请求日志
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            // 打印Http method、调用controller的全路径以及执行方法、请求的IP、请求入参
            log.info("Request URL: {}, Method: {}, Class: {}.{}, IP: {}, Headers: {}, Args: {}",
                    request.getRequestURL().toString(), request.getMethod(),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                    request.getRemoteAddr(), request.getAttribute("User-Agent"), joinPoint.getArgs());
        } catch (Exception e) {
            log.error("doBefore: ", e);
        }
    }

    @Around("aspectPoint()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("Response Args: {}, Time-Consuming: {} ms", result, System.currentTimeMillis() - startTime);
        return result;
    }
}