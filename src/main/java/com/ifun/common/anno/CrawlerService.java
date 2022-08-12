package com.ifun.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/11 15:20
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CrawlerService {

    /**
     * 网站域名简写
     * @return
     */
    String website() default "";
}
