package com.comsince.github.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author comsicne
 *         Copyright (c) [2019] [Meizu.inc]
 * @Time 19-2-18 上午11:07
 **/
@Component
public class SpringApplicationContext implements ApplicationContextAware{
    static Logger logger = LoggerFactory.getLogger(SpringApplicationContext.class);
    public static ApplicationContext pushApplicationContext = null;

    public static Object getBean(String name){
        if(pushApplicationContext != null){
            try {
                return pushApplicationContext.getBean(name);
            }catch (Exception e){
                logger.error("getbean",e);
                return null;
            }
        } else {
            return null;
        }
    }


    public static Object getBeanByType(Class cls){
        if(pushApplicationContext != null){
            try {
                return pushApplicationContext.getBean(cls);
            }catch (Exception e){
                logger.error("getbean",e);
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("init applicationContext");
        pushApplicationContext = applicationContext;
    }
}
