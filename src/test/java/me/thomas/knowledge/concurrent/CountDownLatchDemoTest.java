package me.thomas.knowledge.concurrent;

import me.thomas.knowledge.concurrent.task.OrderTask;
import org.junit.Test;

/**
 * Created by Thomas on 2016/1/3.
 */
public class CountDownLatchDemoTest {

    private CountDownLatchDemo latchDemo = new CountDownLatchDemo();
    private OrderTask orderTask = new OrderTask();

    @Test
    public void testTaskTimes() throws Exception {
        long exhaust = latchDemo.taskTimes(1000, orderTask);
        System.out.println(exhaust);
    }
}