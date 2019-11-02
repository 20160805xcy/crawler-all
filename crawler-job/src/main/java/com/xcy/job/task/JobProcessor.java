package com.xcy.job.task;

import com.xcy.job.pojo.JobInfo;
import com.xcy.job.utils.SalaryUtil;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/28 0:01
 * @Version v1.0
 */
@Component
public class JobProcessor implements PageProcessor {

    private String url = "https://search.51job.com/list/040000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";


    @Override
    public void process(Page page) {
        //解析页面,获取招聘信息详情的url地址
        List<Selectable> nodes = page.getHtml().css("div#resultList div.el").nodes();


        //判断获取到的集合是否为空
        if (nodes.size() == 0) {
            //如果为空,则表示这是招聘详情页,解析页面,获取招聘信息并保存数据库
            this.saveJobInfo(page);
        } else {
            //如果不为空,则表示这是列表页,解析出详情页的url地址,放到任务队列中
            for (Selectable selectable : nodes) {
                //获取url地址
                String jobInfoUrl = selectable.links().toString();
                //System.out.println("jb== "+jobInfoUrl);
                //把获取到的url地址放到任务栏中
                page.addTargetRequest(jobInfoUrl);
            }
            //获取下一页的url
            String bkUrl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
            //System.out.println("bk== " + bkUrl);
            page.addTargetRequest(bkUrl);
        }


    }

    //解析页面,获取招聘信息并保存数据库
    private void saveJobInfo(Page page) {
        JobInfo jobInfo = new JobInfo();

        Html html = page.getHtml();

        jobInfo.setCompanyName(html.css("div.cn p.cname a", "text").toString());
        //因为有的招聘信息,没有写上班的公司地址信息,但是bmsg一定会有一个,第二个才是地址信息(防止后台报错,加个判断)
        if(html.css("div.bmsg").nodes().size()>2){
            jobInfo.setCompanyAddr(Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text());
        }
        jobInfo.setCompanyInfo(Jsoup.parse(html.css("div.tmsg").toString()).text());
        jobInfo.setJobName(html.css("div.cn h1", "text").toString());
        String[] texts = html.css("div.cn p.ltype", "text").toString().split("    ");
        //因为有的发布时间栏,经过切割后可能是长度为4,5, 取发布时间根据下标来取
        if (texts.length == 5) {
            jobInfo.setTime(texts[4]);
        }
        if (texts.length == 4) {
            jobInfo.setTime(texts[3]);
        }
        jobInfo.setJobAddr(texts[0]);
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        jobInfo.setUrl(page.getUrl().toString());
        jobInfo.setsalaryMin(SalaryUtil.getLowerSalary(html.css("div.cn strong", "text").toString()));
        jobInfo.setsalaryMax(SalaryUtil.getHigherSalary(html.css("div.cn strong", "text").toString()));

        //把结果保存起来(webmagic保存于内存,后续通过Pipeline将数据获取到并存于数据库)
        page.putField("jobInfo", jobInfo);

    }

    private Site site = Site.me()
            //设置字符编码
            .setCharset("gbk")
            //设置超时时间,单位毫秒ms
            .setTimeOut(10 * 1000)
            //设置重试的时间间隔,单位毫秒ms
            .setRetrySleepTime(3 * 1000)
            //设置抓取时间间隔
            .setSleepTime(1)
            //设置重试次数
            .setRetryTimes(3);

    @Override
    public Site getSite() {
        return site;
    }


    @Autowired
    private SpringDataPipeline springDataPipeline;

    //initialDelay当任务启动后,等待多久执行方法
    //fixedDelay每隔多久执行一次
    @Scheduled(initialDelay = 1000, fixedDelay = 10 * 1000)
    public void process() {
        Spider.create(new JobProcessor())
                //要解析的网页
                .addUrl(url)
                //将结果输出到指定文件夹下(如果注释掉下面这行,则默认会在控制台输出)
                //.addPipeline(new FilePipeline("C:\\Users\\xcy\\Desktop\\result"))
                //指定线程数量,如果不指定,则默认是1个线程在运行
                .thread(10)
                //设置布隆过滤器,对1000万条数据进行去重过滤,如果不指定,则webmagic默认使用HashSetDuplicateRemover过滤器
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                .addPipeline(this.springDataPipeline)
                .run();
    }

}
