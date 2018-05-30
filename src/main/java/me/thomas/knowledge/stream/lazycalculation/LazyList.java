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
//        System.out.println("LazyList Constructor head: " + head);
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public LazyList<T> tail() {
//        System.out.println("head before tail.get: " + head);
        LazyList<T> tailLazyList = tail.get();
//        System.out.println("head after tail.get: " + tailLazyList.head);
        return tailLazyList;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public LazyList<T> filter(Predicate<T> p) {
        System.out.println("head() in filter: " + head);
        if (p.test(head)) {
            System.out.println("return new LazyList");
            return new LazyList<>(
                    head,
                    () ->
                            tail()
                                    .filter(p));
        } else {
            System.out.println("filter next number");
            return tail()
                    .filter(p);
        }
    }

}
