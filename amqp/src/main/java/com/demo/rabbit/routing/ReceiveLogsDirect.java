package com.demo.rabbit.routing;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogsDirect {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.12.85");
        factory.setPort(56720);
        factory.setUsername("baibei");
        factory.setPassword("baibei");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
//    String queueName = channel.queueDeclare().getQueue();

//    if (argv.length < 1){
//      System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
//      System.exit(1);
//    }

//    for(String severity : argv){
//      channel.queueBind(queueName, EXCHANGE_NAME, severity);
//    }

        String queueName = channel.queueDeclare().getQueue();
        System.out.println("queueName =" + queueName);
        channel.queueBind(queueName, EXCHANGE_NAME, "info");

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);

    }
}

