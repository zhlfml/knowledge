package me.thomas.knowledge.concurrent.interrupt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoxinsheng
 * @date 22/11/2017.
 */
public class InterruptBlockQueue {

    static long currentTimeInSecond() {
        return System.currentTimeMillis() / 1000;
    }

    public static void main(String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        Thread t = new Thread(() -> {
            System.out.println("Start Time: " + currentTimeInSecond());
            try {
                String value = queue.take();
                System.out.println("Value: " + value);
            } catch (InterruptedException e) {
                System.out.println("Interrupt Time: " + currentTimeInSecond());
                System.out.println("Value: got nothing");
            }

            System.out.println("End Time: " + currentTimeInSecond());
        });

        t.start();
        try {
            TimeUnit.SECONDS.sleep(3);
            t.interrupt(); // 会触发InterruptedException
            //queue.put("Apple");
        } catch (InterruptedException ignored) {

        }
    }
}
