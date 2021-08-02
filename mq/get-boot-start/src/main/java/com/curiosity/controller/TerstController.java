package com.curiosity.controller;

import com.curiosity.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TerstController {


    @GetMapping("/")
    public String test() throws InterruptedException {
        Thread.sleep(10000);
        return "我我我" + Thread.currentThread().getName();
    }
}
