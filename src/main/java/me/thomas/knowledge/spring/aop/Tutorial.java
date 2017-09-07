package me.thomas.knowledge.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import me.thomas.knowledge.spring.config.ElConfig;

/**
 * 要点：使用aspectj-maven-plugin在编译时织入切面代码。
 *
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
public class Tutorial {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AopConfig.class);
        // 必须先refresh()后才会初始化其他Bean。
        context.refresh();

        DemoAnnotationService annotationService = context.getBean(DemoAnnotationService.class);
        DemoMethodService methodService = context.getBean(DemoMethodService.class);

        annotationService.add();
        methodService.add();

        ScheduledExecutorService executorService = context.getBean(ScheduledExecutorService.class);
        executorService.scheduleAtFixedRate(() -> System.out.println(1), 1, 1, TimeUnit.SECONDS);

        ElConfig elConfig = context.getBean(ElConfig.class);
        elConfig.outputResource();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            context.close();
        }
    }

}
