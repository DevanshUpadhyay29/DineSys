package com.restaurant.admin.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    /**
     * This advice runs before any method in the package 'com.restaurant.admin' or its sub-packages is executed.
     * It logs a simple message indicating the entry into a method.
     */
    @Before("execution(* com.restaurant.admin..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Entering method in controller/service...");
    }

    /**
     * This advice runs after a method in the package 'com.restaurant.admin' returns successfully.
     * It logs a message indicating successful execution of the method along with a placeholder for the result.
     *
     * @param joinPoint Provides reflective access to the state available at the join point.
     * @param result    The returned value from the method.
     */
    @AfterReturning(pointcut = "execution(* com.restaurant.admin..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Exiting method after successful execution...");
    }

    /**
     * This advice runs if any method in the package 'com.restaurant.admin' throws an exception.
     * It logs a message indicating that an exception occurred during method execution.
     *
     * @param joinPoint Provides reflective access to the state available at the join point.
     * @param ex        The exception thrown by the method.
     */
    @AfterThrowing(pointcut = "execution(* com.restaurant.admin..*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("An exception occurred during method execution.");
    }

    /**
     * This advice runs after any method in the package 'com.restaurant.admin' or its sub-packages has completed,
     * regardless of the outcome (successful completion or exception thrown).
     * It logs a message indicating that the method execution is complete.
     *
     * @param joinPoint Provides reflective access to the state available at the join point.
     */
    @After("execution(* com.restaurant.admin..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Method execution complete.");
    }
}