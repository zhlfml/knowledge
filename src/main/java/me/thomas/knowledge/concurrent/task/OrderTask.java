package me.thomas.knowledge.concurrent.task;

import me.thomas.knowledge.algorithm.dp.Fibonacci;

/**
 * 模拟同时下订单抢库存不足的商品的任务
 *
 * Created by Thomas on 2016/1/3.
 */
public class OrderTask implements Task {

    // 该商品的库存数量
    private int left = 12;

    // 使用虚拟机的监视器实现同步
    @Override
    public synchronized boolean execute() {
        System.out.println(Fibonacci.getInstance().fib(20));
        if (left-- > 0) {
            System.out.println(Thread.currentThread().getName() + "抢到一个。");
            return true;
        }
        return false;
    }
}
