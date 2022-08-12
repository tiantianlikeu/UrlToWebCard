package com.ifun.util.service;

import com.ifun.common.anno.CrawlerService;
import com.ifun.crawlers.card.service.CrawlerCommonService;
import com.ifun.util.spring.SpringUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取实现
 */
@Component
public class GetServiceImplUtil {

    /**
     * 根据website获取实现类
     *
     * @param url
     * @return
     */
    public static CrawlerCommonService getCrawlerService(String url) {
        CrawlerCommonService crawlerCommonService = null;
        List<Class<?>> chainListeners = SpringUtil.getBean("getCrawlerServices");
        for (Class<?> chainListenerCls : chainListeners) {
            CrawlerService crawlerService = chainListenerCls.getAnnotation(CrawlerService.class);
            String website = crawlerService.website();
            if (url.contains(website)) {
                crawlerCommonService = SpringUtil.getBean(chainListenerCls);
                break;
            }
        }
        return crawlerCommonService;
    }
}
