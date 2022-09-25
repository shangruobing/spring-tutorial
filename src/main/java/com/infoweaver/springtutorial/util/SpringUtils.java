package com.infoweaver.springtutorial.util;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Ruobing Shang 2022-09-25 21:18
 */
@Slf4j
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * TODO Hot reload will cause the ApplicationContext to fail to update.
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException exception
     */
    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        log.info("=====ApplicationContext=====");
        log.info(String.valueOf(applicationContext));
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * TODO Missing exception handling for bean not found.
     *
     * @param name Bean's name
     * @return Bean
     * @throws NoSuchBeanDefinitionException Not find Bean
     */
    public static Object getBean(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getBean(name);
    }

}
