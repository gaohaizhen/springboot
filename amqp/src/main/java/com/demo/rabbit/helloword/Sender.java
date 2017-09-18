package com.demo.rabbit.helloword;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 * 1.rabbitmq的入门demo
 */
public class Sender {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.12.85");
        factory.setPort(56720);
        factory.setUsername("baibei");
        factory.setPassword("baibei");
        factory.setVirtualHost("/");


        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//Declaring a queue is idempotent（幂等的） - it will only be created if it doesn't exist already
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties.Builder().expiration("2000")
                .replyTo("192.168.12.36").contentEncoding("utf-8").contentType("").build();

        int i = 0;
        while (i != 10000) {
            Thread.sleep(2000);
            channel.basicPublish("", QUEUE_NAME, basicProperties, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }

}
