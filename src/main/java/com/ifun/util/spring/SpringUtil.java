package com.ifun.util.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Component("springUtil")
public class SpringUtil implements ApplicationContextAware {

    private final static Logger logger = LoggerFactory.getLogger(SpringUtil.class);

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取注入对象
     * <p> TODO</p>
     *
     * @param: @param name
     * @param: @return
     * @return: Object
     * @Date :          2015年9月17日 上午11:56:57
     * @throws:
     */
    public static <T> T getBean(String name) {
        try {
            return (T) applicationContext.getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
            logger.error("Can not find bean :" + name);
        }
        return null;
    }

    public static <T> T getBean(Class cls) {
        try {
            return (T) applicationContext.getBean(cls);
        } catch (NoSuchBeanDefinitionException e) {
            logger.error("Can not find bean :" + cls.getName());
        }
        return null;
    }

    public static String diff(String code) {
        String value = "";
        Map<String, String> map = new HashMap<String, String>();
        if (map != null) {
            if (!StringUtils.isEmpty(map.get(code))) {
                value = map.get(code);
            } else {
                value = code;
            }
        }
        return value;
    }
}
