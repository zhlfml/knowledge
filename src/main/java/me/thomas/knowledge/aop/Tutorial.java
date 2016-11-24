package me.thomas.knowledge.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
public class Tutorial {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        DemoAnnotationService annotationService = context.getBean(DemoAnnotationService.class);
        DemoMethodService methodService = context.getBean(DemoMethodService.class);

        annotationService.add();
        methodService.add();

        context.close();
    }

}
