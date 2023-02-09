package com.otus.authservice.config;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;


@Component
public class ContextProvider implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public  void setApplicationContext(@NotNull ApplicationContext applicationContext)  {
        context = applicationContext;
    }

    /**
     * Get a Spring bean by type.
     **/
    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    /**
     * Get a Spring bean by name.
     **/
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
