package com.xcy.webmagic.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * @author xcy
 * @Desc 去重
 * @date 2019/10/27 22:08
 * @Version v1.0
 */
public class DeDuplicateProcess implements PageProcessor {
    @Override
    public void process(Page page) {
        page.addTargetRequest("https://www.itjc8.com/forum-43-1.html");
        page.addTargetRequest("https://www.itjc8.com/forum-43-1.html");
        page.addTargetRequest("https://www.itjc8.com/forum-43-1.html");



    }


    private Site site = Site.me()
            //设置字符编码
            .setCharset("utf8")
            //设置超时时间,单位毫秒ms
            .setTimeOut(10000)
            //设置重试的时间间隔,单位毫秒ms
            .setRetrySleepTime(3000)
            //设置抓取时间间隔
            .setSleepTime(1)
            //设置重试次数
            .setRetryTimes(3);

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new DeDuplicateProcess())
                //要解析的网页
                .addUrl("https://www.itjc8.com/forum-43-1.html")
                //将结果输出到指定文件夹下(如果注释掉下面这行,则默认会在控制台输出)
                //.addPipeline(new FilePipeline("C:\\Users\\xcy\\Desktop\\result"))
                //指定线程数量,如果不指定,则默认是1个线程在运行
                .thread(5)
                //设置布隆过滤器,对1000万条数据进行去重过滤,如果不指定,则webmagic默认使用HashSetDuplicateRemover过滤器
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)));

        //此处断点可以看到webmagic默认使用的是HashSetDuplicateRemover过滤器
        Scheduler scheduler = spider.getScheduler();


        //执行爬虫
        spider.run();
    }
}
