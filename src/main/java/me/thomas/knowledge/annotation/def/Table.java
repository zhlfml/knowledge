package me.thomas.knowledge.annotation.def;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhaoxinsheng
 * @date 8/7/16.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited //Inherited: Only for class extends, https://stackoverflow.com/questions/23973107/how-to-use-inherited-annotation-in-java
public @interface Table {
    String value();
}
