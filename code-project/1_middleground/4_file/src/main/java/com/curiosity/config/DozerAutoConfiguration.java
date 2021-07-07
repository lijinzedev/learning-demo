package com.curiosity.config;

import com.github.dozermapper.core.Mapper;
import com.curiosity.utils.DozerUtils;
import org.springframework.context.annotation.Bean;

/**
 * @Classname DozerAutoConfiguration
 * @Description
 * @Date 2021/7/7 14:23
 * @Created by curiosity
 */
public class DozerAutoConfiguration {
    @Bean
    public DozerUtils getDozerUtils(Mapper mapper) {
        DozerUtils dozerUtils = new DozerUtils(mapper);
        return dozerUtils;
    }
}