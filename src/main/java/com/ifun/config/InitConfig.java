package com.ifun.config;

import com.ifun.common.anno.CrawlerService;
import com.ifun.util.classes.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/11 15:40
 */
@Configuration
public class InitConfig {

    /**
     * 日志组件
     */
    private static final Logger logger = LoggerFactory.getLogger(InitConfig.class);

    /**
     * 扫描CrawlerService注解
     *
     * @return
     */
    @Bean("getCrawlerServices")
    public List<Class<?>> getCrawlerServices() {
        String packageName = "com.ifun";
        logger.info("CrawlerServices实现注解扫描的包路劲：{}", packageName);
        List<Class<?>> annoClasses = ClassUtil.getAnnoClasses(CrawlerService.class, packageName);
        logger.info("CrawlerServices实现注解扫描的包个数：{}", annoClasses.size());
        return annoClasses;
    }
}
