package com.curiosity.controller;

import com.curiosity.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PublishController {
    public final OrderService orderService;

    @GetMapping("/")
    public void test(){
        orderService.makeOrder("1","1",1);
    }
}
