package me.thomas.knowledge.gc;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author zhaoxinsheng
 * @date 2018/9/16.
 */
public class MyHashSet<E> extends HashSet<E> {

    private Collection<? extends E> c;

    public MyHashSet(Collection<? extends E> c) {
        super(c);
        this.c = c;
    }
}
