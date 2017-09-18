package com.demo.rabbit.helloword;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Receiver {

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
//Note that we declare the queue here, as well. Because we might start the consumer before the publisher,
// we want to make sure the queue exists before we try to consume messages from it.
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("等待接收消息");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                StringBuilder acc = new StringBuilder();
                properties.appendPropertyDebugStringTo(acc);
                System.out.println("AMQP.BasicProperties的内容是'" + acc.toString() + "'");
                String message = new String(body, "UTF-8");
                System.out.println("envelope'" + envelope.toString() + "'");
                System.out.println("收到的消息内容是'" + message + "'");
            }


            public void handleConsumeOk(String consumerTag) {
                System.out.println(" handleConsumeOk");

            }

            public void handleCancelOk(String consumerTag) {
                System.out.println(consumerTag + "--consumerTag ---- handleCancelOk");
            }

            public void handleCancel(String consumerTag) throws IOException {
                System.out.println(consumerTag + "-- handleCancel");
            }

            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
                System.out.println(consumerTag + "-- handleShutdownSignal");
            }

            public void handleRecoverOk(String consumerTag) {
                System.out.println(consumerTag + "--handleRecoverOk");
            }


        };



        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
