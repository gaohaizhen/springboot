package com.demo.rabbit.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 * We will use a direct exchange instead. The routing algorithm behind a direct exchange
 * is simple - a message goes to the queues whose binding key exactly matches the routing key of the message.
 */
public class EmitLogDirect {

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

        String severity = getSeverity(argv);//路由键

        int i = 0;
        while (i < 1000) {
            ++i;
            String message = getMessage(argv);
            message = message + i;
            Thread.sleep(1000l);
            if(i%2 == 0){
                channel.basicPublish(EXCHANGE_NAME, "info", null, message.getBytes("UTF-8"));
            }else{
                channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes("UTF-8"));
            }

            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }


        channel.close();
        connection.close();
    }

    private static String getSeverity(String[] strings) {
        if (strings.length < 1)
            return "info";
        return strings[0];
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 2)
            return "Hello World!";
        return joinStrings(strings, " ", 1);
    }

    private static String joinStrings(String[] strings, String delimiter, int startIndex) {
        int length = strings.length;
        if (length == 0) return "";
        if (length < startIndex) return "";
        StringBuilder words = new StringBuilder(strings[startIndex]);
        for (int i = startIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}

