package com.curiosity.config;

import com.curiosity.handler.DataHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Classname BaseHolder
 * @Description 上下文持有
 * @Date 2021/4/2 17:58
 * @Created by curiosity
 */
@Component
public class BaseHolder implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    private static Map<String, DataHandler> handlers;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BaseHolder.applicationContext = applicationContext;
        handlers = applicationContext.getBeansOfType(DataDbHandler.class);
    }



    /**
     * 这里约定， 定义 bean 的时候， beanName 统一转小写
     */
    public static DataDbHandler getDbDataHandler(String beanName) {
        if (StringUtils.isNotEmpty(beanName)) {
            beanName = beanName.toLowerCase();
        }
        return handlers.get(beanName);
    }

}
