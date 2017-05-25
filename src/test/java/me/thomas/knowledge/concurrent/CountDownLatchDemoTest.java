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
    private CurlTask curlTask = new CurlTask("http://s-analytics-ci.patsnap.local:8081/analytics/1.0/feedback?feedback_type=new_search_ui");

    @Test
    public void testTaskTimes() throws Exception {
        long exhaust = latchDemo.taskTimes(1, curlTask);
        System.out.println(exhaust);
    }
}