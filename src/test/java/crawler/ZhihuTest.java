package crawler;

import com.ifun.ToCardBootApplication;
import com.ifun.crawlers.card.UrlToCardCrawler;
import com.ifun.crawlers.card.service.CrawlerCommonService;
import com.ifun.util.spring.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The type Zhihu test.
 *
 * @Author: tiantianlikeU 。
 * @Date: 2022 /8/12 09:32
 */
@SpringBootTest(classes = ToCardBootApplication.class)
@RunWith(SpringRunner.class)
public class ZhihuTest {




    /**
     * Crawler 知乎专栏 test.
     */
    @Test
    public void crawlerZhihuZhuanlanTest() throws Exception {
        CrawlerCommonService crawlerCommonService = SpringUtil.getBean(CrawlerCommonService.class);
        String url = "https://zhuanlan.zhihu.com/p/262929418";
        UrlToCardCrawler urltoCardCrawler = new UrlToCardCrawler("urlToCardCrawler", true,
                url, crawlerCommonService);
        urltoCardCrawler.start(1);
    }

    /**
     * Crawler 知乎 问答 test.
     */
    @Test
    public void crawlerZhihuTest() throws Exception {
        CrawlerCommonService crawlerCommonService = SpringUtil.getBean(CrawlerCommonService.class);
        String url = "https://www.zhihu.com/question/538424813/answer/2621609233";
        UrlToCardCrawler urltoCardCrawler = new UrlToCardCrawler("urlToCardCrawler", true,
                url, crawlerCommonService);
        urltoCardCrawler.start(1);
    }
}
