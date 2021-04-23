package com.curiosity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Curiosity
 * @Date: 2021/4/7 22:16
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @GetMapping("get")
    private Object get(Authentication authentication) {
        return authentication;
    }
}
