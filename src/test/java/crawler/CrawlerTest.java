package crawler;

import org.junit.Test;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/10 16:27
 */
public class CrawlerTest {


    /**
     * 根据url获取基本信息测试
     */
    @Test
    public void UrlGetInfoTest() throws Exception {
        String url = "https://ruanyifeng.com/road/2004/2004-02-14-juliette-drouet.html";
        UrlToCardCrawlerTest urltoCardCrawler = new UrlToCardCrawlerTest("urlToCardCrawler", true,
                url);
        urltoCardCrawler.start(1);
    }
}
