package com.ifun.crawlers.card.service.zhihu.impl;

import com.ifun.common.anno.CrawlerService;
import com.ifun.crawlers.card.service.CrawlerCommonService;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/11 15:12
 */
@Service("crawlerZhihuService")
@CrawlerService(website = "zhihu.com")
public class CrawlerZhihuService extends CrawlerCommonService {

    /**
     * 重写获取知乎网站内容第一张图片的方法
     * @param doc the doc
     * @return
     */
    @Override
    public String getContentImg(Document doc) {
        return super.getContentImg(doc);
    }
}
