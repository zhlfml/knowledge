package me.thomas.knowledge.aop;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * NOT WORK -- NEED TO IMPROVE
 *
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
@Component
public aspect LogAspect2 {

    pointcut annotationPointCut(): @annotation(me.thomas.knowledge.aop.Action);

    after() : annotationPointCut() {
        MethodSignature signature = (MethodSignature) thisJoinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("AnnotationService: " + action.name());
    }

    before() : execution(* me.thomas.knowledge.aop.DemoMethodService.* (..)) {
        MethodSignature signature = (MethodSignature) thisJoinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("MethodService: " + method.getName());
    }
}
