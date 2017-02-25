package me.thomas.knowledge.concurrent;

import me.thomas.knowledge.concurrent.task.CurlTask;
import me.thomas.knowledge.concurrent.task.OrderTask;
import org.junit.Test;

/**
 * Created by Thomas on 2016/1/3.
 */
public class CountDownLatchDemoTest {

    private CountDownLatchDemo latchDemo = new CountDownLatchDemo();
    private OrderTask orderTask = new OrderTask();
    private CurlTask curlTask = new CurlTask("http://127.0.0.1:3000/?text=%E6%88%91%E5%96%9C%E6%AC%A2%E8%BF%99%E9%97%A8%E6%8A%80%E6%9C%AF&to=de");

    @Test
    public void testTaskTimes() throws Exception {
        long exhaust = latchDemo.taskTimes(5, curlTask);
        System.out.println(exhaust);
    }
}