package me.thomas.knowledge.stream.lazycalculation.allinone;

import java.util.function.Predicate;
import java.util.function.Supplier;

interface MyList<T> {

    T head();

    MyList<T> tail();

    default boolean isEmpty() {
        return true;
    }

    default void printAll() {
        if (!isEmpty()) {
            System.out.println(head());
            tail()
                    .printAll();
        }
    }
}

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
        return p.test(head) ?
                new LazyList<>(head,
                        () ->
                                tail()
                                        .filter(p)) :
                tail()
                        .filter(p);
    }

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    public static LazyList<Integer> primes(LazyList<Integer> numbers) {
        return new LazyList<>(numbers.head(),
                () ->
                        primes(
                                numbers.tail()
                                        .filter(n -> n % numbers.head() != 0)
                )
        );
    }

    public static void main(String[] args) {
        primes(from(2)).printAll();
    }
}
