package com.demo.rabbit.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.12.85");
        connectionFactory.setPort(56720);
        connectionFactory.setUsername("baibei");
        connectionFactory.setPassword("baibei");
        connectionFactory.setVirtualHost("/");

        //断开后自动重连
        connectionFactory.setAutomaticRecoveryEnabled(true);


        Connection connection = connectionFactory.newConnection("NewTaskConnection");
        Channel channel = connection.createChannel();
        System.out.println("getClientProperties--" + connection.getClientProperties());
        System.out.println("getServerProperties--" + connection.getServerProperties());

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        String[] strings = {"messagepersistence........................."};
        String message = getMessage(strings);

        int i = 1;
        while (i != 10) {
            channel.basicPublish("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));

            i++;
        }
        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}