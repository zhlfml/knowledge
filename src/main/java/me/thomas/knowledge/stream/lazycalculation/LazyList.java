package me.thomas.knowledge.stream.lazycalculation;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author zhaoxinsheng
 * @date 31/01/2018.
 */
public class LazyList<T> implements MyList<T> {

    private final T head;
    private final Supplier<LazyList<T>> tail;

    public LazyList(T head, Supplier<LazyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public LazyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public LazyList<T> filter(Predicate<T> p) {
        return isEmpty() ?
                this :
                p.test(head()) ?
                        new LazyList<>(head(), () -> tail().filter(p)) :
                        tail().filter(p);
    }

}
