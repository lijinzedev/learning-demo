package com.curiosity.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Setter
@Component
@ConfigurationProperties(prefix = "canal")
public class CanalConfig {
}
