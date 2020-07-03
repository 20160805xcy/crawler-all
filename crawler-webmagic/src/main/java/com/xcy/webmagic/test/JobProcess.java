package com.xcy.webmagic.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author xcy
 * @Desc webmagic入门案例
 * @date 2019/10/27 15:55
 * @Version v1.0
 */
public class JobProcess implements PageProcessor {

    //解析页面
    @Override
    public void process(Page page) {
        //已经将此页面存于web目录下的itjc8网页.html文件下,以后研究下方3种方式可以拿来做页面分析
        System.out.println(page.getHtml());


        ////解析返回的数据page,并且把解析的结果放到ResultItems中
        ////方式一:采用css选择器
        //page.putField("result1", page.getHtml().css("th.new.forumtit a.s.xst").all());
        //
        ////方式二:采用xPath语法
        //page.putField("result2", page.getHtml().xpath("//*/tr/th[@class=\"new forumtit\"]/a[@class=\"s xst\"]").all());
        //
        ////方式三:采用正则表达式
        ////将所有a标签文本信息进行内容匹配,筛选出含有java字样的
        //page.putField("result3", page.getHtml().css("th.new.forumtit a.s.xst").regex(".*java.*").all());
        //
        //
        ////获取第一条数据
        //page.putField("result4 使用get()方式,获取第一条数据     : ", page.getHtml().css("th.new.forumtit a.s.xst").regex(".*java.*").get());
        //page.putField("result5 使用toString()方式,获取第一条数据: ", page.getHtml().css("th.new.forumtit a.s.xst").regex(".*java.*").toString());
        //
        ////获取链接,注意这里links能匹配获取到所有a标签href,以thread-6723-1-1.html结尾的链接地址.然后request会将所有的满足条件的链接存起来.循环请求
        ////需要额外注意哦,page会每次都执行所有的putField("","")方法.
        //page.addTargetRequests(page.getHtml().css("th.new.forumtit a.s.xst").links().regex(".*thread-6723-1-1.html$").all());
        //page.putField("result6 ", page.getHtml().css(".vwthdts.z span").all());
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


    //主函数,执行爬虫
    public static void main(String[] args) {
        Spider.create(new JobProcess())
                //要解析的网页
                //.addUrl("https://www.itjc8.com/forum-43-1.html")
                .addUrl("https://jdbx.jd.com/")
                //将结果输出到指定文件夹下(如果注释掉下面这行,则默认会在控制台输出)
                .addPipeline(new FilePipeline("C:\\Users\\xcy\\Desktop\\result"))
                //指定线程数量,如果不指定,则默认是1个线程在运行
                .thread(5)
                .run();
    }
}


/*
结果:

方式一:
        result1: [<a href="https://www.itjc8.com/thread-6723-1-1.html" onclick="atarget(this)" class="s xst">SSM框架开发学生信息系统笔记源码静态页数据库全2018年12</a>, <a href="https://www.itjc8.com/thread-6695-1-1.html" onclick="atarget(this)" class="s xst">Elasticsearch高级课程入门到核心技术讲解与项目实战2019</a>, <a href="https://www.itjc8.com/thread-6673-1-1.html" onclick="atarget(this)" class="s xst">Java SSM开发视频考试系统项目实战笔记源码素材数据全2018年</a>, <a href="https://www.itjc8.com/thread-6659-1-1.html" onclick="atarget(this)" class="s xst">java 源码角度深入拆解Tomcat与Jetty探索java中间件2019新</a>, <a href="https://www.itjc8.com/thread-6644-1-1.html" onclick="atarget(this)" class="s xst">java ssm框架项目实战开发酒店管理系统[全]2019年</a>, <a href="https://www.itjc8.com/thread-6627-1-1.html" onclick="atarget(this)" class="s xst">JAVA健康项目实战视频讲义资料全2019年8月新品共36G</a>, <a href="https://www.itjc8.com/thread-6569-1-1.html" onclick="atarget(this)" class="s xst">1811期_毕业设计_全套视频教程（视频、源码、课件）2018年</a>, <a href="https://www.itjc8.com/thread-6565-1-1.html" onclick="atarget(this)" class="s xst">最新git从入门到项目实战教程2019年8月新课</a>, <a href="https://www.itjc8.com/thread-6554-1-1.html" onclick="atarget(this)" class="s xst">2019马士兵最新算法与数据结构教程</a>, <a href="https://www.itjc8.com/thread-6548-1-1.html" onclick="atarget(this)" class="s xst">2019 马士兵最新java设计模式视频教程[9.4G]</a>, <a href="https://www.itjc8.com/thread-6490-1-1.html" onclick="atarget(this)" class="s xst">三周搞定互联网大厂面试石杉java进阶分布式面试2019</a>, <a href="https://www.itjc8.com/thread-6478-1-1.html" onclick="atarget(this)" class="s xst">深入解读Java12与java13新特性2019年9月23结课</a>, <a href="https://www.itjc8.com/thread-6421-1-1.html" onclick="atarget(this)" class="s xst">分布式系统框架Spring+Redis+SSO视频课程价值80全2018</a>, <a href="https://www.itjc8.com/thread-6425-1-1.html" onclick="atarget(this)" class="s xst">S硅谷_宋HK老师_JDBC核心技术视频教程资料全(2019新版)</a>, <a href="https://www.itjc8.com/thread-6403-1-1.html" onclick="atarget(this)" class="s xst">Java性能优化全面性能调优与电商项目调优实战2019新</a>, <a href="https://www.itjc8.com/thread-6386-1-1.html" onclick="atarget(this)" class="s xst">跟传智燕青一起学RabbitMQ消息队列RabbitMQ版本3.7.3 2018年9月</a>, <a href="https://www.itjc8.com/thread-6363-1-1.html" onclick="atarget(this)" class="s xst">千锋最新ElasticSearch6实战教程2018</a>, <a href="https://www.itjc8.com/thread-6321-1-1.html" onclick="atarget(this)" class="s xst">L果java微服务Api网关框架视频课程2018价值300+</a>, <a href="https://www.itjc8.com/thread-6315-1-1.html" onclick="atarget(this)" class="s xst">D内在线Java高手加薪课1906期-26班 2019</a>, <a href="https://www.itjc8.com/thread-6306-1-1.html" onclick="atarget(this)" class="s xst">Java进阶主流java技术与热门开源项目框架中间件项目解读2019</a>]
方式二:
        result2: [<a href="https://www.itjc8.com/thread-6723-1-1.html" onclick="atarget(this)" class="s xst">SSM框架开发学生信息系统笔记源码静态页数据库全2018年12</a>, <a href="https://www.itjc8.com/thread-6695-1-1.html" onclick="atarget(this)" class="s xst">Elasticsearch高级课程入门到核心技术讲解与项目实战2019</a>, <a href="https://www.itjc8.com/thread-6673-1-1.html" onclick="atarget(this)" class="s xst">Java SSM开发视频考试系统项目实战笔记源码素材数据全2018年</a>, <a href="https://www.itjc8.com/thread-6659-1-1.html" onclick="atarget(this)" class="s xst">java 源码角度深入拆解Tomcat与Jetty探索java中间件2019新</a>, <a href="https://www.itjc8.com/thread-6644-1-1.html" onclick="atarget(this)" class="s xst">java ssm框架项目实战开发酒店管理系统[全]2019年</a>, <a href="https://www.itjc8.com/thread-6627-1-1.html" onclick="atarget(this)" class="s xst">JAVA健康项目实战视频讲义资料全2019年8月新品共36G</a>, <a href="https://www.itjc8.com/thread-6569-1-1.html" onclick="atarget(this)" class="s xst">1811期_毕业设计_全套视频教程（视频、源码、课件）2018年</a>, <a href="https://www.itjc8.com/thread-6565-1-1.html" onclick="atarget(this)" class="s xst">最新git从入门到项目实战教程2019年8月新课</a>, <a href="https://www.itjc8.com/thread-6554-1-1.html" onclick="atarget(this)" class="s xst">2019马士兵最新算法与数据结构教程</a>, <a href="https://www.itjc8.com/thread-6548-1-1.html" onclick="atarget(this)" class="s xst">2019 马士兵最新java设计模式视频教程[9.4G]</a>, <a href="https://www.itjc8.com/thread-6490-1-1.html" onclick="atarget(this)" class="s xst">三周搞定互联网大厂面试石杉java进阶分布式面试2019</a>, <a href="https://www.itjc8.com/thread-6478-1-1.html" onclick="atarget(this)" class="s xst">深入解读Java12与java13新特性2019年9月23结课</a>, <a href="https://www.itjc8.com/thread-6421-1-1.html" onclick="atarget(this)" class="s xst">分布式系统框架Spring+Redis+SSO视频课程价值80全2018</a>, <a href="https://www.itjc8.com/thread-6425-1-1.html" onclick="atarget(this)" class="s xst">S硅谷_宋HK老师_JDBC核心技术视频教程资料全(2019新版)</a>, <a href="https://www.itjc8.com/thread-6403-1-1.html" onclick="atarget(this)" class="s xst">Java性能优化全面性能调优与电商项目调优实战2019新</a>, <a href="https://www.itjc8.com/thread-6386-1-1.html" onclick="atarget(this)" class="s xst">跟传智燕青一起学RabbitMQ消息队列RabbitMQ版本3.7.3 2018年9月</a>, <a href="https://www.itjc8.com/thread-6363-1-1.html" onclick="atarget(this)" class="s xst">千锋最新ElasticSearch6实战教程2018</a>, <a href="https://www.itjc8.com/thread-6321-1-1.html" onclick="atarget(this)" class="s xst">L果java微服务Api网关框架视频课程2018价值300+</a>, <a href="https://www.itjc8.com/thread-6315-1-1.html" onclick="atarget(this)" class="s xst">D内在线Java高手加薪课1906期-26班 2019</a>, <a href="https://www.itjc8.com/thread-6306-1-1.html" onclick="atarget(this)" class="s xst">Java进阶主流java技术与热门开源项目框架中间件项目解读2019</a>]
方式三:
        result3: [<a href="https://www.itjc8.com/thread-6673-1-1.html" onclick="atarget(this)" class="s xst">Java SSM开发视频考试系统项目实战笔记源码素材数据全2018年</a>, <a href="https://www.itjc8.com/thread-6659-1-1.html" onclick="atarget(this)" class="s xst">java 源码角度深入拆解Tomcat与Jetty探索java中间件2019新</a>, <a href="https://www.itjc8.com/thread-6644-1-1.html" onclick="atarget(this)" class="s xst">java ssm框架项目实战开发酒店管理系统[全]2019年</a>, <a href="https://www.itjc8.com/thread-6627-1-1.html" onclick="atarget(this)" class="s xst">JAVA健康项目实战视频讲义资料全2019年8月新品共36G</a>, <a href="https://www.itjc8.com/thread-6548-1-1.html" onclick="atarget(this)" class="s xst">2019 马士兵最新java设计模式视频教程[9.4G]</a>, <a href="https://www.itjc8.com/thread-6490-1-1.html" onclick="atarget(this)" class="s xst">三周搞定互联网大厂面试石杉java进阶分布式面试2019</a>, <a href="https://www.itjc8.com/thread-6478-1-1.html" onclick="atarget(this)" class="s xst">深入解读Java12与java13新特性2019年9月23结课</a>, <a href="https://www.itjc8.com/thread-6403-1-1.html" onclick="atarget(this)" class="s xst">Java性能优化全面性能调优与电商项目调优实战2019新</a>, <a href="https://www.itjc8.com/thread-6321-1-1.html" onclick="atarget(this)" class="s xst">L果java微服务Api网关框架视频课程2018价值300+</a>, <a href="https://www.itjc8.com/thread-6315-1-1.html" onclick="atarget(this)" class="s xst">D内在线Java高手加薪课1906期-26班 2019</a>, <a href="https://www.itjc8.com/thread-6306-1-1.html" onclick="atarget(this)" class="s xst">Java进阶主流java技术与热门开源项目框架中间件项目解读2019</a>]
 */