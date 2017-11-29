package me.thomas.knowledge.concurrent.completablefuture;

import java.util.Random;

/**
 * 模拟汇率查询
 *
 * @author zhaoxinsheng
 * @date 29/11/2017.
 */
public class Exchange {

    private final static Random rnd = new Random();

    public static double rate() {
        Delay.delay(1);
        return rnd.nextDouble() * 100;
    }
}
