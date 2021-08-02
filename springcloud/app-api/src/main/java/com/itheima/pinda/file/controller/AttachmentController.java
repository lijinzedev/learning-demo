package com.itheima.pinda.file.controller;

import com.itheima.pinda.file.properties.FileServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文件服务--附件处理控制器
 */
@RestController
@RequestMapping("/attachment")
@Slf4j
@EnableConfigurationProperties(FileServerProperties.class)
public class AttachmentController {
    @Resource
    public FileServerProperties fileServerProperties;

    public boolean upload() {
        final boolean type = fileServerProperties.isType();
        return type;
    }


}