package com.curiosity.config;

import com.curiosity.properties.FileServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname LocalAutoConfigure
 * @Description
 * @Date 2021/7/7 16:51
 * @Created by curiosity
 */
@Configuration
@Slf4j
@EnableConfigurationProperties(FileServerProperties.class)
@ConditionalOnProperty(name = "file.file.type",havingValue = "LOCAL")
public class FileAutoConfigure {
}
