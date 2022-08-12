package com.ifun.crawlers.card.service.zhihu.impl;

import com.ifun.common.anno.CrawlerService;
import com.ifun.crawlers.card.service.CrawlerCommonService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/11 15:12
 */
@Service("crawlerZhihuService")
@CrawlerService(website = "zhihu.com")
public class CrawlerZhihuService extends CrawlerCommonService {

    /**
     * 重写获取知乎网站内容第一张图片的方法
     *
     * @param doc the doc
     * @return
     */
    @Override
    public String getContentImg(Document doc) {
        String imgUrl = "";
        Elements body = doc.getElementsByTag("body");
        // 对知乎内容模块做定向解析
        List<Element> contentList = body.get(0).getElementsByTag("main");
        if (!contentList.isEmpty()) {
            Elements imgElements = contentList.get(0).getElementsByTag("img");
            if (imgElements.size() > 0) {
                // 获取内容第一张图片
                imgUrl = imgElements.get(0).attr("src");
            }
        }
        return imgUrl;
    }
}
