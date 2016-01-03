package me.thomas.knowledge.concurrent;

import me.thomas.knowledge.concurrent.task.Task;

import java.util.concurrent.CountDownLatch;

/**
 * 通过这个例子可以得出结论：await()和countDown()两个方法，必须是一个在父线程中调用，另一个在子线程中被调用。
 *
 * Created by Thomas on 2016/1/3.
 */
public class CountDownLatchDemo {

    public long taskTimes(int threads, final Task task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threads);

        for (int i = 0; i < threads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        // 在每个线程的开始处调用await()，如果startGate的计数器不等于0，该线程都必须被阻塞，直到计数器变成0。
                        startGate.await();
                        try {
                            // 原文调用的是Runnable.run(), 说明将Runnable task作为一个普通的方法来调用。
                            task.execute();
                        } finally {
                            // 放在finally语句中，已确保endGate的计数器递减。
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {
                        // ignore
                    }
                }
            };
            t.start();
        }

        long startTime = System.currentTimeMillis();
        startGate.countDown();
        endGate.await();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

}
