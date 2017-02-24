package me.thomas.knowledge.aop.another.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
@Configuration
public class AppConfig {

    @Bean
    public ExecutorService executor() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
