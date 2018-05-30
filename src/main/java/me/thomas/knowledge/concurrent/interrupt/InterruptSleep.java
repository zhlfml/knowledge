package me.thomas.knowledge.concurrent.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaoxinsheng
 * @date 22/11/2017.
 */
public class InterruptSleep {
    
    static long currentTimeInSecond() {
        return System.currentTimeMillis() / 1000;
    }

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            System.out.println("Start Time: " + currentTimeInSecond());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Interrupt Time: " + currentTimeInSecond());
            }
            System.out.println("End Time: " + currentTimeInSecond());
        });

        t.start();
        try {
            TimeUnit.SECONDS.sleep(3);
            t.interrupt(); // 会触发InterruptedException
        } catch (InterruptedException ignored) {

        }
    }
}
