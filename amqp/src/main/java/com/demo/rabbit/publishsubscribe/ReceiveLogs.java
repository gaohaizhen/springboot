package com.demo.rabbit.publishsubscribe;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * fanout
 */
public class ReceiveLogs {
  private static final String EXCHANGE_NAME = "logs";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("192.168.12.85");
    connectionFactory.setPort(56720);
    connectionFactory.setUsername("baibei");
    connectionFactory.setPassword("baibei");
    connectionFactory.setVirtualHost("/");

    Connection connection = connectionFactory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
    String queueName = channel.queueDeclare().getQueue();
    System.out.println("queueName="+queueName);
    channel.queueBind(queueName, EXCHANGE_NAME, "");

    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");

        channel.basicAck(envelope.getDeliveryTag(),false);
      }
    };
    channel.basicConsume(queueName, false, consumer);
  }
}
