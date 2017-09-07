package me.thomas.knowledge.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * @author zhaoxinsheng
 * @date 08/09/2017.
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        System.out.println("[MyInstantiationAwareBeanPostProcessor] Constructor");
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessor] postProcessPropertyValues()");
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessor] postProcessBeforeInstantiation()");
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("[MyInstantiationAwareBeanPostProcessor] postProcessAfterInitialization()");
        return super.postProcessAfterInitialization(bean, beanName);
    }
}
