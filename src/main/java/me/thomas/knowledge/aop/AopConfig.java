package me.thomas.knowledge.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
@Configuration
@ComponentScan("me.thomas.knowledge.aop")
@EnableAspectJAutoProxy
public class AopConfig {

}
