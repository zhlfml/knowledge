package me.thomas.knowledge.jms.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Thomas on 2016/1/22.
 */
public class Consumer implements Runnable {

    private String name;

    public Consumer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");

        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            final Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("MessageQueue");

            MessageConsumer consumer = session.createConsumer(destination);

            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        String content = ((TextMessage) message).getText();
                        if (content != null) {
                            System.out.println(name + ": " + content);
                        }
                        // 接收消息时，如果在接收消息方法正常完成后没有调用commit方法，
                        // 消息就会被标记为未被传送，JMS提供者会将这些消息重新传送给消费者.
                        // 所以在收到消息后必须立即commit,向JMS提供者确认已经收到消息.
                        session.commit();
                    } catch (JMSException e) {
                        Thread.currentThread().interrupt();
                    }

                }
            });
        } catch (JMSException jmse) {
            Thread.currentThread().interrupt();
        } finally {
            // 只要session不关闭，这个线程就一直处于就绪状态。
//            try {
//                if (session != null) {
//                    session.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (JMSException ignored) {
//
//            }
        }
    }
}
