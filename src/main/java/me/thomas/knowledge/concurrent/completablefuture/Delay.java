package me.thomas.knowledge.concurrent.completablefuture;

import java.util.concurrent.TimeUnit;

/**
 * 模拟网络延时
 *
 * @author zhaoxinsheng
 * @date 29/11/2017.
 */
public class Delay {

    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ignored) {
        }
    }
}
