package me.thomas.knowledge.designpatterns.observer.event;

/**
 * @author zhaoxinsheng
 * @date 8/26/16.
 */
@FunctionalInterface
public interface Listener<T> {

    void apply(T args);

}
