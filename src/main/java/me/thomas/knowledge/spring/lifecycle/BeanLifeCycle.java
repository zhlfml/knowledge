package me.thomas.knowledge.spring.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 用于学习和了解Bean的生命周期，AbstractApplicationContext.refresh()方法执行过程。
 * 参考网页：http://www.cnblogs.com/zrtqsk/p/3735273.html
 *
 * @author zhaoxinsheng
 * @date 08/09/2017.
 */
public class BeanLifeCycle {

    public static void main(String[] args) {
        System.out.println("现在开始初始化容器");
        ApplicationContext factory = new ClassPathXmlApplicationContext("spring/beans.xml");
        System.out.println("容器初始化成功");
        //得到Person，并使用
        Person person = factory.getBean("zhlfml", Person.class);
        System.out.println(person);
        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }
}
