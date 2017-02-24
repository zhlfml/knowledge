package me.thomas.knowledge.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * @author zhaoxinsheng
 * @date 24/11/2016.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class ElConfig {

    @Value("I Love You!")
    private String normal;

    @Value("#{systemProperties['os.name']}")
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double randomNumber;

    // 若想使用@Value获取到配置文件中的值，则必须配置PropertySourcesPlaceholderConfigurer对象。
    @Value("${book.name}")
    private String bookName;

    @Autowired
    private Environment environment;

    public ElConfig() {
        System.out.println("init");
    }

    // 在定义@Bean的时候，static关键字不是必须的。
    // 但这里可能是继承了BeanFactoryPostProcessor接口的原因，必须使用static关键字。
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource() {
        System.out.println(normal);
        System.out.println(osName);
        System.out.println(randomNumber);
        System.out.println(bookName);
        // 注入的配置文件内容，还可以通过Environment对象中获得。
        System.out.println(environment.getProperty("book.author"));
    }

}
