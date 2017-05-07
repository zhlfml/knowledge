package me.thomas.knowledge.aop;

import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 使用aspectj-maven-plugin编译
 *
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
public aspect LogAspect2 {

    public LogAspect2() {}

    pointcut annotationPointCut(): @annotation(me.thomas.knowledge.aop.Action);

    after() : annotationPointCut() {
        MethodSignature signature = (MethodSignature) thisJoinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("LogAspect2 AnnotationService: " + action.name());
    }

    before() : execution(* me.thomas.knowledge.aop.DemoMethodService.* (..)) {
        MethodSignature signature = (MethodSignature) thisJoinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("LogAspect2 MethodService: " + method.getName());
    }
}
