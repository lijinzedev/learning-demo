package com.curiosity;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Classname Main
 * @Description
 * @Date 2021/1/15 13:14
 * @Created by lijinze
 */
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement (proxyTargetClass = true)
@MapperScan("com.curiosity.dao")
@EnableConfigurationProperties
@Slf4j
public class Main {
    @SneakyThrows
    public static void main(String[] args) throws UnknownHostException {
        final ConfigurableApplicationContext application = SpringApplication.run(Main.class, args);
        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "Swagger文档: \t\thttp://{}:{}/doc.html\n\t" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }
    }


