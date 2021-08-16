package com.curiosity.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Classname MqttProperties
 * @Description
 * @Date 2021/8/16 13:41
 * @Created by lijinze
 */
@ConfigurationProperties(
        prefix = "mqtt.client"
)
@Data
public class MqttProperties {

    private String username;

    private String password;

    private String serverURI;

    private String clientId;

    private int keepAliveInterval;

    private int connectionTimeout;
}
