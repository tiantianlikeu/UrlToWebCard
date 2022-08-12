# 链接转网页卡片爬虫,URL TO WEB CARD

### 项目介绍：

```html
由于有通过一个网页链接获取该网页的基本信息（标题，描述，图片等）的需求，类似于qq或者微信聊天中发送一个链接后，聊天窗口自动将链接解析成一个小卡片。

如果需要让qq可以正确识别并解析需要按照qq的解析规则对网页进行设置meta标签，如下：
<meta itemprop="name" content="标题">
<meta itemprop="Description" content="描述内容">
<meta itemprop="image" content="显示的图片URL">

但是由于每个网站网页的排版结构都不一样，有的网站没有meta 或结构化资料itemprop，部分网站并不会为了适应这个规则而做调整。
所以这个项目便是通过使用爬虫定向对某网站定向解析来达到我们的需要的数据
```

### 技术实现：

```html
java
爬虫使用：WebCollector
细节实现：开启爬虫 (UrlToCardCrawler) => 调用通用服务解析（CrawlerCommonService）=> 如果没有解析到数据,则调用定向网站的解析服务（CrawlerZhihuService） => 得到数据
我们要做的事：编写具体网站的定向解析服务。如项目中的 CrawlerZhihuService
编码：编写定向网站解析服务的时候需要添加@CrawlerService(website = "zhihu.com")注解，以便项目可以扫描并正常获取服务。
定向网站的解析，首先最好去获取meta标签，这个是SEO相关，大部分网站里面为了让爬虫识别重点内容都会配置。如果实在获取不到，可以去解析网页排版，拿到内容信息和图片。
```

