package me.thomas.knowledge.stream.lazycalculation;

/**
 * @author zhaoxinsheng
 * @date 31/01/2018.
 */
public interface MyList<T> {

    T head();

    MyList<T> tail();

    default boolean isEmpty() {
        return true;
    }

    default void printAll() {
        if (!isEmpty()) {
            System.out.println(head());
            tail().printAll();
        }
    }
}
