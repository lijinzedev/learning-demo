package com.curiosity.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Produce {
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
        Connection connection = connectionFactory.newConnection("生产者");
        // 3. 通过连接获取通道
        final Channel channel = connection.createChannel();
        // 4. 通过创建交换机，声明队列，路由key，发送接受消息
        // 参数
        // 1-队列名
        // 2-持久化 非持久化会存盘吗
        // 3-排他性 是否是独占队列
        // 4-是否自动删除 最后一个消费者消费完毕是否删除
        // 5-携带附加参数

        String message = "hello direct_exchange";
        // 6. 发送消息到队列
        channel.basicPublish("direct_exchange","direct1" ,null,message.getBytes(StandardCharsets.UTF_8));
        // 7. 关闭连接
        channel.close();
        // 8. 关闭通道
        connection.close();
    }
}
