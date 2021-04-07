package com.curiosity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description
 * @Date 2021/4/7 10:30
 * @Created by curiosity
 */
@RestController
@Slf4j
@RequestMapping
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "test";
    }
}
