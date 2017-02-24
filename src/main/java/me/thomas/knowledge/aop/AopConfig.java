package me.thomas.knowledge.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import me.thomas.knowledge.spring.config.ElConfig;

/**
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
@Configuration
@ComponentScan("me.thomas.knowledge.aop")
@Import(ElConfig.class)
@EnableAspectJAutoProxy
public class AopConfig {

}
