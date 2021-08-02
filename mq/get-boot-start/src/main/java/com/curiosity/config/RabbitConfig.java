package com.curiosity.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    /**
     * 声明交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_boot_exchange", true, false);
    }

    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue smsQueue() {
        return new Queue("sms", true);
    }

    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue duanxinQueue() {
        return new Queue("duanxin", true);
    }

    /**
     * 声明队列
     *
     * @return
     */
    @Bean
    public Queue emailQueue() {
        return new Queue("email", true);
    }

    @Bean
    public Binding smsBinding() {
      return   BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding duanxinBingding(){
        return BindingBuilder.bind(duanxinQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding emailBingding(){
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
}
