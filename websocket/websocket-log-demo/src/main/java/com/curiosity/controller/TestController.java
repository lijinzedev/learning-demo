package com.curiosity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Classname TestController
 * @Description
 * @Date 2021/4/14 11:14
 * @Created by curiosity
 */
@RestController
@RequestMapping
public class TestController {

    @GetMapping("/")
    public Object get() {
        return new HashMap<String, String>() {
            {
                put("1","1");
                put("2","1");
            }
        };
    }
}
