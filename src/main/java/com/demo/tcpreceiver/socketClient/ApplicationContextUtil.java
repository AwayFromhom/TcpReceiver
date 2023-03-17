package com.demo.tcpreceiver.socketClient;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static <T> T autowire(T bean){
        applicationContext.getAutowireCapableBeanFactory()
                .autowireBeanProperties(bean, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
        return bean;
    }
}
