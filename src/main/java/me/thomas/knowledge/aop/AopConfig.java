package me.thomas.knowledge.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import me.thomas.knowledge.spring.config.ElConfig;

/**
 * 使用aspectj-maven-plugin在编译时织入切面代码，
 * 这里不用配置LogAspect2的bean。
 * 而且从测试结果发现连使用了@AspectJ注解的类也被织入到了目标代码中。
 * 所以这里可以不用配置@EnableAspectJAutoProxy注解也能启用切面功能了。
 *
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
@Configuration
@ComponentScan("me.thomas.knowledge.aop")
@Import(ElConfig.class)
//@EnableAspectJAutoProxy
public class AopConfig {

//    @Bean
//    public LogAspect2 logAspect2() {
//        return LogAspect2.aspectOf();
//    }
}
