package com.itheima.pinda.file.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.io.File;

/**
 * 文件策略配置属性类
 */
@Data
@ConfigurationProperties(prefix = "pinda.file")
@RefreshScope
public class FileServerProperties {

    private boolean type = true;
}