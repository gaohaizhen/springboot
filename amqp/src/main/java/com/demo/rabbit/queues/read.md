**1.Message acknowledgment**

    Message acknowledgment是为了防止消费者挂掉了消息丢失的情景

**1.1 Forgotten acknowledgment**

    Forgotten acknowledgment是一个很严重的错误，需要在控制台监控

It's a common mistake to miss the basicAck. 
It's an easy error, but the consequences are serious. 
Messages will be redelivered when your client quits 
(which may look like random redelivery), 
but RabbitMQ will eat more and more memory 
as it won't be able to release any unacked messages.

 
 
 In order to debug this kind of mistake you can use rabbitmqctl 
 to print the messages_unacknowledged field:
 
` sudo rabbitmqctl list_queues name messages_ready messages_unacknowledged`
 
 On Windows, drop the sudo:
 
 `rabbitmqctl.bat list_queues name messages_ready messages_unacknowledged`
 
 
 
 
 **2. Message durability**
 
    Message durability是为了防止rabbitmq server挂掉后消息丢失的情景
 
 We have learned how to make sure that even if the consumer dies, the task isn't lost. But our tasks will still be lost if RabbitMQ server stops.
 When RabbitMQ quits or crashes it will forget the queues and messages unless you tell it not to. Two things are required to make sure that messages aren't lost: we need to mark both the queue and messages as durable.
 
 First, we need to make sure that RabbitMQ will never lose our queue. In order to do so, we need to declare it as durable:
 
 `boolean durable = true;`
 
 `channel.queueDeclare("task_queue", durable, false, false, null);`
 
 At this point we're sure that the task_queue queue won't be lost even if RabbitMQ restarts. Now we need to mark our messages as persistent - by setting MessageProperties (which implements BasicProperties) to the value PERSISTENT_TEXT_PLAIN.
 
`import com.rabbitmq.client.MessageProperties;`


 `channel.basicPublish("", "task_queue",MessageProperties.PERSISTENT_TEXT_PLAIN,
             message.getBytes());`
             
             
             
 Note on message persistence
 
 Marking messages as persistent doesn't fully guarantee that a message won't be lost. Although it tells RabbitMQ to save the message to disk, there is still a short time window when RabbitMQ has accepted a message and hasn't saved it yet. Also, RabbitMQ doesn't do  fsync(2) for every message -- it may be just saved to cache and not really written to the disk. The persistence guarantees aren't strong, but it's more than enough for our simple task queue. If you need a stronger guarantee then you can use publisher confirms  https://www.rabbitmq.com/confirms.html.
 
 
 
 
 
 