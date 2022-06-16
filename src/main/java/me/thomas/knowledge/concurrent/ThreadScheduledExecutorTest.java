package me.thomas.knowledge.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoxinsheng
 * @date 9/25/16.
 */
public class ThreadScheduledExecutorTest {

    // 明明有10个线程可用，但为什么是每隔6秒输出一次？因为只提交了一个任务啊。
    public static void main(String[] args) {
        //ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // corePoolSize可以理解为最多可并行执行的任务数。但前提是需要喂这么多任务才行。
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getId() + " @1 => " + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, 10, 3, TimeUnit.SECONDS);

        // 提交第二个任务
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getId() + " @2 => " + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, 10, 3, TimeUnit.SECONDS);

        // 提交第二个任务
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getId() + " @3 => " + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, 10, 3, TimeUnit.SECONDS);
    }
}
