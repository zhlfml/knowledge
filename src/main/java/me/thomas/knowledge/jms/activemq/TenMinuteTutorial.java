package me.thomas.knowledge.jms.activemq;

/**
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
