package me.thomas.knowledge.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(me.thomas.knowledge.aop.Action)")
    public void annotationPointCut() {

    }

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("AnnotationService: " + action.name());
    }

    @Before("execution(* me.thomas.knowledge.aop.DemoMethodService.* (..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("MethodService: " + method.getName());
    }
}
