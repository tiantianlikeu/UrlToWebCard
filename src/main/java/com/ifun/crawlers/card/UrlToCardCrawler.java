package com.ifun.crawlers.card;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.alibaba.fastjson.JSON;
import com.ifun.crawlers.card.service.CrawlerCommonService;
import com.ifun.entity.UrlWebCard;
import com.ifun.util.service.GetServiceImplUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网页链接转卡片的爬虫
 *
 * @Author: tiantianlikeU 。
 * @Date: 2022 /8/10 16:17
 */
public class UrlToCardCrawler extends BreadthCrawler {

    /**
     * 日志组件
     */
    private static final Logger logger = LoggerFactory.getLogger(UrlToCardCrawler.class);

    private CrawlerCommonService crawlerCommonService;

    private String url;

    /**
     * 爬虫的基本配置
     *
     * @param crawlPath the crawl path
     * @param autoParse the auto parse
     * @param url       the url
     */
    public UrlToCardCrawler(String crawlPath, boolean autoParse,
                            String url, CrawlerCommonService crawlerCommonService) {
        super(crawlPath, autoParse);
        addSeedAndReturn(url).type("label");
        setThreads(1);
        getConf().setTopN(1);
        this.url = url;
        this.crawlerCommonService = crawlerCommonService;
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        UrlWebCard urlWebCard = new UrlWebCard();
        Document doc = page.doc();
        urlWebCard.setUrl(url);
        // 获取标题
        String title = crawlerCommonService.getTitle(doc);

        urlWebCard.setTitle(title);

        // 获取描述，如果描述为空，则赋空字符或者去内容里面选取文字
        String description = crawlerCommonService.getDescription(doc);
        if (StringUtils.isBlank(description)) {
            CrawlerCommonService crawlerService = GetServiceImplUtil.getCrawlerService(url);
            if (crawlerService != null) {
                description = crawlerService.getDescription(doc);
            } else {
                logger.warn("没有该域名{}的解析类", url);
            }
        }
        urlWebCard.setDescription(description);
        // 获取图片，这里需要适配各个平台的网站
        String contentImgUrl = crawlerCommonService.getContentImg(doc);
        if (StringUtils.isBlank(contentImgUrl)) {
            CrawlerCommonService crawlerService = GetServiceImplUtil.getCrawlerService(url);
            if (crawlerService != null) {
                contentImgUrl = crawlerService.getContentImg(doc);
            } else {
                logger.warn("没有该域名{}的解析类", url);
            }
        }
        urlWebCard.setImgUrl(contentImgUrl);
        // todo 将对象urlWebCard更新到数据库，同步到es
        String webCardJson = JSON.toJSONString(urlWebCard);
        System.out.println("this is a web card: " + webCardJson);
    }

    @Override
    public Page getResponse(CrawlDatum crawlDatum) {
        try {
            HttpRequest request = new HttpRequest(crawlDatum);
            Page page = request.responsePage();
            return page;
        } catch (Exception e) {
            return getResponse(crawlDatum);
        }
    }


}
