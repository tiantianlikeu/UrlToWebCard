package com.ifun.crawlers.card.service;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * The type Crawler common service.
 *
 * @Author: tiantianlikeU 。
 * @Date: 2022 /8/11 15:07
 */
public abstract class CrawlerCommonService {


    /**
     * 获取网页标题
     *
     * @param doc
     * @return
     */
    public String getTitle(Document doc) {
        Elements titleElements = doc.getElementsByTag("title");
        if (titleElements.size() > 0) {
            String title = titleElements.get(0).html();
            return title;
        }
        return null;
    }

    /**
     * 获取网页的描述
     *
     * @param doc the doc
     * @return the description
     */
    public String getDescription(Document doc) {
        Elements elementsByAttributeValue = doc.getElementsByAttributeValue("name", "description");
        if (elementsByAttributeValue.size() > 0) {
            String description = elementsByAttributeValue.get(0).attr("content");
            return description;
        }
        return null;
    }

    /**
     * 获取分享图
     *
     * @param doc the doc
     * @return the content img
     */
    public String getContentImg(Document doc) {
        Elements elementsByAttributeValue = doc.getElementsByAttributeValue("itemprop", "image");
        if (elementsByAttributeValue.size() > 0) {
            String content = elementsByAttributeValue.get(0).attr("content");
            return content;
        }
        return null;
    }
}
