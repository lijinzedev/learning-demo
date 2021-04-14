package com.curiosity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: Curiosity
 * @Date: 2021/4/6 22:09
 * @Description:
 */
@SpringBootApplication
@EnableScheduling
public class WebSocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }
}
