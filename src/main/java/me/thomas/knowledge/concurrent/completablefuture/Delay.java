package me.thomas.knowledge.concurrent.completablefuture;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 模拟网络延时
 *
 * @author zhaoxinsheng
 * @date 29/11/2017.
 */
public class Delay {

    private static final Random rnd = new Random();

    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ignored) {}
    }

    public static void delayRandom(int lowestMilliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(lowestMilliSeconds + rnd.nextInt(3000));
        } catch (InterruptedException ignored) {}
    }
}
