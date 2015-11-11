package com.efriandika.learn.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author efriandika
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    public void setApplicationContext(final ApplicationContext context)
            throws BeansException {
        CONTEXT = context;
    }

    public static <T> T getBean(Class<T> clazz) { return CONTEXT.getBean(clazz); }
}
