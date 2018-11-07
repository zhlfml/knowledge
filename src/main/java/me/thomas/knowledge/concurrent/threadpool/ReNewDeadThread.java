package me.thomas.knowledge.concurrent.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试在线程池中的线程，因为异常退出时，线程池会不会重建线程来填充，已达到默认的线程数大小
 * 试验之前，我的推断是会的，因为线程池本来就会维护一定数量的线程，如果线程数达不到指定的最大的线程，那么当有新任务到来时，会创建新线程。
 * 重大发现，executorService.execute()和submit()是不同的，submit()内部会封装异常，所以线程不会挂掉。但是execute()如果没有捕获异常，那么当抛出异常时，该线程会挂掉。但是线程池会重新创建线程以满足最低线程数大小。
 *
 * @author zhaoxinsheng
 * @date 2018/11/7.
 */
public class ReNewDeadThread {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1, (r) -> {
            Thread t = new Thread(r);
            t.setName("thread " + atomicInteger.incrementAndGet());
            t.setUncaughtExceptionHandler((t1, e) -> e.printStackTrace());
            return t;
        });
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int j = i;
            Future<Integer> future = executorService.submit(() -> {
                if (j % 10 == 9) {
                    throw new IllegalStateException("I hate number ends with 9: " + j);
                }
                System.out.println(Thread.currentThread().getName() + " print " + j);
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Shouldn't happen");
                }
                return j;
            });
            try {
                System.out.println("Got value " + future.get());
            } catch (ExecutionException e) {
                System.out.println("Error occurred: " + e.getCause().getMessage());
            }
        }
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }
}
