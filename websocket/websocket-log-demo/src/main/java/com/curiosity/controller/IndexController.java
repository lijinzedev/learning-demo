package com.curiosity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.*;

/**
 * @Classname IndexController
 * @Description
 * @Date 2021/4/13 17:06
 * @Created by curiosity
 */
@Controller
@RequestMapping
@Slf4j
public class IndexController {


    @GetMapping("index")
    public String index() {

        return "log";
    }



}
