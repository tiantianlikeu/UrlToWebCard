package com.ifun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com"})
@EnableScheduling
public class ToCardBootApplication {

    private static final Logger logger = LoggerFactory.getLogger(ToCardBootApplication.class);

    public static void main(String[] args) {
        logger.debug("启动url转网页卡片服务");
        System.setProperty("test.timezone", "GMT +08");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication app = new SpringApplication(ToCardBootApplication.class);
        app.run(args);
    }
}
