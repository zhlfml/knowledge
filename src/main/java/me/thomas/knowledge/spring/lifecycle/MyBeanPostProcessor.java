package me.thomas.knowledge.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author zhaoxinsheng
 * @date 08/09/2017.
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        System.out.println("[MyBeanPostProcessor] Constructor");
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("[MyBeanPostProcessor] postProcessBeforeInitialization()");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("[MyBeanPostProcessor] postProcessAfterInitialization()");
        return o;
    }
}
