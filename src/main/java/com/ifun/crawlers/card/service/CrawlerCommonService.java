package com.ifun.crawlers.card.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Crawler common service.
 *
 * @Author: tiantianlikeU 。
 * @Date: 2022 /8/11 15:07
 */
public abstract class CrawlerCommonService {


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
     * 获取网页正文内容第一张图片
     *
     * @param doc the doc
     * @return the content img
     */
    public String getContentImg(Document doc) {
        Elements body = doc.getElementsByTag("body");
        // 先找id 为content的
        List<Element> contentList = body.get(0).getElementsByTag("div").
                stream().filter(div -> div.attr("id").contains("content"))
                .collect(Collectors.toList());
        if (!contentList.isEmpty()) {
            Elements imgElements = contentList.get(0).getElementsByTag("img");
            if (imgElements.size() > 0) {
                // 获取内容第一张图片
                String imgUrl = imgElements.get(0).attr("src");
                return imgUrl;
            }
        }
        return null;
    }

}
