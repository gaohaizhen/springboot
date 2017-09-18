package com.demo.rabbit.topics;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。
 * 符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。因此“audit.#”能够匹配到“audit.irs.corporate”，
 * 但是“audit.*” 只会匹配到“audit.irs”
 */
public class ReceiveLogsTopic {

  private static final String EXCHANGE_NAME = "topic_logs";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.12.85");
    factory.setPort(56720);
    factory.setUsername("baibei");
    factory.setPassword("baibei");
    factory.setVirtualHost("/");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
    AMQP.Queue.DeclareOk declareOk = channel.queueDeclare("queue_topic_logs",false,false,false,null);
//    String queueName = channel.queueDeclare().getQueue();

    if (argv.length < 1) {
      System.err.println("Usage: ReceiveLogsTopic [binding_key]...");
      System.exit(1);
    }

    for (String bindingKey : argv) {
      channel.queueBind("queue_topic_logs", EXCHANGE_NAME, bindingKey);
    }

    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
        channel.basicAck(envelope.getDeliveryTag(),false);
      }
    };
    channel.basicConsume("queue_topic_logs", false, consumer);
  }
}

