package crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.ifun.entity.UrlWebCard;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 网页链接转卡片的爬虫,网站适配调试用
 *
 * @Author: tiantianlikeU 。
 * @Date: 2022 /8/10 16:17
 */
public class UrlToCardCrawlerTest extends BreadthCrawler {

    /**
     * 日志组件
     */
    private static final Logger logger = LoggerFactory.getLogger(UrlToCardCrawlerTest.class);

    private String url;

    /**
     * 爬虫的基本配置
     *
     * @param crawlPath the crawl path
     * @param autoParse the auto parse
     * @param url       the url
     */
    public UrlToCardCrawlerTest(String crawlPath, boolean autoParse,
                                String url) {
        super(crawlPath, autoParse);
        addSeedAndReturn(url).type("label");
        setThreads(1);
        getConf().setTopN(1);
        this.url = url;
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        UrlWebCard urlWebCard = new UrlWebCard();
        Document doc = page.doc();
        urlWebCard.setUrl(url);
        String title = "";
        Elements titleElements = doc.getElementsByTag("title");
        if (titleElements.size() > 0) {
            title = titleElements.get(0).html();
            logger.info("title: {}", title);
        }

        // 获取描述，
        String description = "";
        Elements elementsByAttributeValue = doc.getElementsByAttributeValue("name", "description");
        if (elementsByAttributeValue.size() > 0) {
            description = elementsByAttributeValue.get(0).attr("content");
        }
        // 获取图片
        Elements body = doc.getElementsByTag("body");
        // 先找id 为content的
        List<Element> contentList = body.get(0).getElementsByTag("div").
                stream().filter(div -> div.attr("id").contains("content"))
                .collect(Collectors.toList());
        String imgUrl = "";
        if (!contentList.isEmpty()) {
            Elements imgElements = contentList.get(0).getElementsByTag("img");
            if (imgElements.size() > 0) {
                // 获取内容第一张图片
                imgUrl = imgElements.get(0).attr("src");
            }
        }
        System.out.println("imgUrl:" + imgUrl);
        System.out.println("description:" + description);
        System.out.println("title:" + title);
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
