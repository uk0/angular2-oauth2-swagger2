package com.sparksdev.flo.common.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author bengill
 */
@Service
public class FloContextAware implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {

        FloContextAware.applicationContext = applicationContext;
    }

    public static void refeshApplicationContext(final ApplicationContext applicationContext) {
        FloContextAware.applicationContext = applicationContext;
    }


    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

}
