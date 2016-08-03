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
        long[] array = new long[16];
        Random random = new Random();

        // setup
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextLong() % 100;
        }
        System.out.println("Initial Array: " + Arrays.toString(array));

        // run
        ForkJoinTask sortTask = new SortTask(array);
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(sortTask);
        pool.shutdown();

        pool.awaitTermination(30, TimeUnit.SECONDS);

        System.out.println("After Sort Array: " + Arrays.toString(array));
    }
}
