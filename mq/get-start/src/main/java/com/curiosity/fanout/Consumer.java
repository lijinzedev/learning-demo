package com.curiosity.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 所有的中间件都是基于tcp/ip 协议基础上构建新型协议，mq遵循amqp
        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.173.10");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        // 2. 创建连接
        Connection connection = connectionFactory.newConnection("消费者");
        // 3. 通过连接获取通道
        final Channel channel = connection.createChannel();
        String queueName = "q1";
//3.接受内容
        channel.basicConsume(queueName, true, (s, delivery) -> {
            System.out.println(new String("收到消息是"+new String(delivery.getBody())));
        }, s -> {
                    System.out.println("接受消息发生异常");
                }
                );
        System.out.println();
        System.in.read();
        channel.close();
        connection.close();
    }
}
