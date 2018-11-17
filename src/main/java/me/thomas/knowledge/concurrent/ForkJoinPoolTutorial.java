package me.thomas.knowledge.concurrent;

import me.thomas.knowledge.concurrent.task.SortTask;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoxinsheng
 * @date 7/26/16.
 */
public class ForkJoinPoolTutorial {

    public static void main(String[] args) throws InterruptedException {
        long[] array_a = new long[16];
        long[] array_b = new long[16];
        Random random = new Random();

        // setup
        for (int i = 0; i < array_a.length; i++) {
            array_a[i] = random.nextLong() % 100;
        }
        System.out.println("Initial Array A: " + Arrays.toString(array_a));
        for (int i = 0; i < array_b.length; i++) {
            array_b[i] = random.nextLong() % 100;
        }
        System.out.println("Initial Array B: " + Arrays.toString(array_b));

        // run
        ForkJoinTask sortTask_a = new SortTask(array_a);
        ForkJoinTask sortTask_b = new SortTask(array_b);
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(sortTask_a);
        pool.submit(sortTask_b);
        pool.shutdown();

        pool.awaitTermination(30, TimeUnit.SECONDS);

        System.out.println("After Sort Array A: " + Arrays.toString(array_a));
        System.out.println("After Sort Array B: " + Arrays.toString(array_b));
        // 结果输出正确，也就说是ForkJoinPool可以作为一个全局对象来使用
    }
}
