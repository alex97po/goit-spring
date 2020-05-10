package com.pogorelov.goitspring.aspect;

import com.pogorelov.goitspring.domain.Event;
import com.pogorelov.goitspring.loggers.EventLogger;
import com.pogorelov.goitspring.loggers.FileEventLogger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    private final Map<Class<?>, Integer> counter = new HashMap<>();

    private FileEventLogger fileEventLogger;

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}

    @Pointcut("allLogEventMethods() && within(* .*Console*Logger)")
    private void logEventInsideConsoleLoggers() {}

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("BEFORE : " + joinPoint.getTarget().getClass().getSimpleName()
        + " " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "allLogEventMethods()", returning = "retVal")
    public void logAfter(Object retVal) {
        log.info("Returned value: " + retVal);
    }

    @AfterThrowing(pointcut = "allLogEventMethods()", throwing = "ex")
    public void logAfterThrow(Throwable ex) {
        log.warn("Thrown: " + ex);
    }

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        if (!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz)+1);
    }

    @Around("logEventInsideConsoleLoggers() && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Object evt) throws Throwable {
        Class<?> clazz = jp.getTarget().getClass();
        if (counter.get(clazz) < 10) {
            jp.proceed(new Object[]{evt});
        } else {
            fileEventLogger.logEvent((Event) evt);
        }
    }
}
