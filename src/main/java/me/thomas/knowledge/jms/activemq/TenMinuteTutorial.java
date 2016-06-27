package me.thomas.knowledge.jms.activemq;

/**
 * 参考文章: https://segmentfault.com/a/1190000004247661
 * ACTIVEMQ控制台: http://localhost:8161/admin/
 * 登录的用户名和密码: admin/admin
 *
 * Created by Thomas on 2016/1/21.
 */

public class TenMinuteTutorial {

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        for (int i = 0; i < 5; i++) {
            new Thread(new Consumer("Consumer " + i)).start();
        }
    }
}
