package me.thomas.knowledge.stream.lazycalculation;

/**
 * @author zhaoxinsheng
 * @date 31/01/2018.
 */
public class Empty<T> implements MyList<T> {

    @Override
    public T head() {
        throw new UnsupportedOperationException();
    }

    @Override
    public MyList<T> tail() {
        throw new UnsupportedOperationException();
    }

}
