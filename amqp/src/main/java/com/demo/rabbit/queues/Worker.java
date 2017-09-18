package com.demo.rabbit.queues;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Worker {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.12.85");
        connectionFactory.setPort(56720);
        connectionFactory.setUsername("baibei");
        connectionFactory.setPassword("baibei");
        connectionFactory.setVirtualHost("/");

        connectionFactory.setConnectionTimeout(5000);


        final Connection connection = connectionFactory.newConnection("WorkerConnection");
        final Channel channel = connection.createChannel();

        System.out.println("getClientProperties--" + connection.getClientProperties());
        System.out.println("getServerProperties--" + connection.getServerProperties());

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


        /**
         * we can use the basicQos method with the prefetchCount = 1 setting.
         * This tells RabbitMQ not to give more than one message to a worker at a time.
         * Or, in other words, don't dispatch a new message to a worker
         * until it has processed and acknowledged the previous one.
         * Instead, it will dispatch it to the next worker that is not still busy
         */
        channel.basicQos(1); // accept only one unack-ed message at a time (see below)

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleConsumeOk(String consumerTag){
                System.out.println("handleConsumeOk--consumerTag="+consumerTag);

            }
            @Override
            public void handleCancelOk(String consumerTag){
                System.out.println("handleCancelOk--consumerTag="+consumerTag);

            }

            @Override
            public void handleCancel(String consumerTag) throws IOException{
                System.out.println("handleCancel--consumerTag="+consumerTag);
            }


            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig){
                System.out.println("handleShutdownSignal--consumerTag="+consumerTag);
            }


            @Override
            public void handleRecoverOk(String consumerTag){
                System.out.println("handleRecoverOk--consumerTag="+consumerTag);
            }
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'"+"---deliveryTag---"+envelope.getDeliveryTag());
                try {
                    for (char ch : message.toCharArray()) {
                        if (ch == '.') {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException _ignored) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                } finally {
                    System.out.println(" [x] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);

                }
            }
        };

        /**
         * Manual message acknowledgments are turned on by default.
         * In previous examples we explicitly turned them off via the autoAck=true flag.
         * It's time to set this flag to false and send a proper acknowledgment from the worker,
         * once we're done with a task.Manual message acknowledgments are turned on by default.
         * In previous examples we explicitly turned them off via the autoAck=true flag.
         * It's time to set this flag to false and send a proper acknowledgment from the worker,
         * once we're done with a task.
         */
        boolean autoAck = false;//Manual message acknowledgments
        //set this flag to false and send a proper acknowledgment from the worker,
        //once we're done with a task.
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);
    }

}