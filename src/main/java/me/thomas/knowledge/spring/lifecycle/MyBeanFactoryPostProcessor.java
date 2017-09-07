package me.thomas.knowledge.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author zhaoxinsheng
 * @date 08/09/2017.
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        System.out.println("[MyBeanFactoryPostProcessor] Constructor");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("[MyBeanFactoryPostProcessor] postProcessBeanFactory() ... change bean definition { phone = 13776066695 }");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("zhlfml");
        beanDefinition.getPropertyValues().addPropertyValue("phone", "137****6695");
        // 不存在的属性不能设置
        // beanDefinition.getPropertyValues().addPropertyValue("noThisProperty", "null");
    }
}
