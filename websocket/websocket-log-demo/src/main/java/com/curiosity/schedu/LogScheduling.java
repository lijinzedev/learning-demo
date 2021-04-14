package com.curiosity.schedu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Classname LogScheduling
 * @Description
 * @Date 2021/4/14 9:40
 * @Created by curiosity
 */
@Component
@Slf4j
public class LogScheduling {
    @Scheduled(fixedDelay = 200)
    public void test() {
        log.info("当前时间为{}", LocalDateTime.now());
    }
}
