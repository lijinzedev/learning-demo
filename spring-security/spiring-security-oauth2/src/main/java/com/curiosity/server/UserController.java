package com.curiosity.server;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserController
 * @Description
 * @Date 2021/4/7 15:09
 * @Created by curiosity
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/getUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication;
    }
}
