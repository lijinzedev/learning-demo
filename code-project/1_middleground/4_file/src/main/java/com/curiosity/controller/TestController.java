package com.curiosity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestController
 * @Description
 * @Date 2021/7/7 13:27
 * @Created by curiosity
 */
@RequestMapping("/test")
@RestController
public class TestController {


    @GetMapping
    public String hello() {
        return "hello world";
    }
}
