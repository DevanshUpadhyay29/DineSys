package com.restaurant.user.aspect;

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

    // Before any method in user-service executes, log an entry message.
    @Before("execution(* com.restaurant.user..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    // After a method in user-service returns successfully, log an exit message.
    @AfterReturning(pointcut = "execution(* com.restaurant.user..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with return value: {}", joinPoint.getSignature(), result);
    }

    // If a method in user-service throws an exception, log an error message.
    @AfterThrowing(pointcut = "execution(* com.restaurant.user..*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in method: {} - Message: {}", joinPoint.getSignature(), ex.getMessage(), ex);
    }

    // After any method in user-service has completed execution (either normally or exceptionally), log completion.
    @After("execution(* com.restaurant.user..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Completed execution of method: {}", joinPoint.getSignature());
    }
}
