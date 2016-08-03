package me.thomas.knowledge.concurrent;

import me.thomas.knowledge.algorithm.dp.Fibonacci;

import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by zhaoxs on 2016/1/25 0025.
 */
public class FutureDemo {

    public static void main(String[] args) {
        final Fibonacci fibonacci = Fibonacci.getInstance();

        // 1. new FutureTask
        FutureTask<Long> futureTask = new FutureTask<>(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return fibonacci.fib(8);
            }
        });
        // 一定要在新线程中启动这个任务
        new Thread(futureTask).start();

        // 2. executor.submit(Callable callable)
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Long> future = executor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return fibonacci.fib(48);
            }
        });
        // 只接受一个任务，做完任务后需要shutdown
        executor.shutdown();

        try {
            // future.get()方法是会阻塞当前线程的操作
            System.out.println("newFutureTask: " + futureTask.get() + " at " + new Date().getTime());
            System.out.println("executor.submit: " + future.get() + " at " + new Date().getTime());
            System.out.println("over");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
