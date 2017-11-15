package com.epam.danilchican.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {
    }

    @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEventInsideFileLoggers() {
    }

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint point) {
        System.out.println("BEFORE: " + point.getTarget().getClass().getSimpleName() + " " + point.getSignature().getName());
    }
}
