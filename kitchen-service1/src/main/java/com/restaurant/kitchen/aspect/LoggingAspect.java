package com.restaurant.kitchen.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.restaurant.kitchen..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.restaurant.kitchen..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with return value: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "execution(* com.restaurant.kitchen..*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in method: {} - Message: {}", joinPoint.getSignature(), ex.getMessage(), ex);
    }

    @After("execution(* com.restaurant.kitchen..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Completed execution of method: {}", joinPoint.getSignature());
    }
}
