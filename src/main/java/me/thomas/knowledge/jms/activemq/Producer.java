package me.thomas.knowledge.jms.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Thomas on 2016/1/21.
 */
public class Producer implements Runnable {

    @Override
    public void run() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");

        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("MessageQueue");
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            for (int i = 0; i < 1000; i++) {
                TextMessage message = session.createTextMessage("hello everyone!" + System.currentTimeMillis());
                producer.send(message);
                session.commit();
            }
        } catch (JMSException jmse) {
            Thread.currentThread().interrupt();
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException ignored) {

            }
        }
    }
}
