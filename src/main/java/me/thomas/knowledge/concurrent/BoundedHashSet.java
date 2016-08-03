package me.thomas.knowledge.concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 使用Semaphore为容器设置边界
 *
 * @author zhaoxinsheng
 * @date 6/21/16.
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(Set<T> set, int bound) {
        this.set = Collections.synchronizedSet(set);
        this.semaphore = new Semaphore(bound);
    }

    /**
     * 如果元素被添加进容器中,则不会释放许可.由于许可是有限的,所以该容器的容量也有边界.
     *
     * @param e 要被添加的元素
     * @return
     * @throws InterruptedException
     */
    public boolean add(T e) throws InterruptedException {
        System.out.println("semaphore.getQueueLength() = " + semaphore.getQueueLength());
        semaphore.acquire(); // 该操作会阻塞线程
        boolean added = false;
        try {
            added = set.add(e);
        } finally {
            if (!added) {
                // 如果元素没有添加进容器,则立即释放许可.
                semaphore.release();
            }
        }
        return added;
    }

    public boolean remove(T e) {
        boolean removed = set.remove(e);
        if (removed) {
            semaphore.release();
        }
        return removed;
    }

    public Iterator<T> iterator() {
        return set.iterator();
    }

    public static void main(String[] args) {
        BoundedHashSet<String> set = new BoundedHashSet<>(new HashSet<>(), 5);
        for (int i = 0; i < 9; i++) {
            final int number = i;
            new Thread(() -> {
                try {
                    set.add(String.valueOf(number));
                } catch (InterruptedException e) {
                    // ignore
                }
            }).start();
        }

        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
