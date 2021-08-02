package com.curiosity.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
@RabbitListener(queues = {"sms"})
public class SMSListener {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("接收到了的订单信息是："+message);
    }
}
