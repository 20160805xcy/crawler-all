package com.xcy.crawlerjd.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xcy.crawlerjd.pojo.Item;
import com.xcy.crawlerjd.service.ItemService;
import com.xcy.crawlerjd.util.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/7/14 15:34
 * @Version v1.0
 */
@Component
//开启定时任务
@EnableScheduling
public class ItemTask {

    @Autowired
    private HttpClientUtils httpClientUtils;
    @Autowired
    private ItemService itemService;

    //json解析的工具类
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //当下载完成后,间隔多长时间进行下一次任务
    @Scheduled(fixedRate = 100 * 1000)
    public void itemTask() throws Exception {
        //声明需要解析的初始化地址
        String url = "https://search.jd.com/Search?keyword=手机&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&cid2=653&cid3=655&s=112&click=0&page=";
        System.out.println("sssssfff");
        for(int i = 1;i< 10; i=i+2){
            String html = httpClientUtils.doGetHtml(url + i);
            System.out.println(html);
            this.pase(html);


        }
    }

    //解析页面,获取商品信息并保存
    private void pase(String html) throws IOException {
        //解析html,获取document
        Document doc = Jsoup.parse(html);
        //获取spu信息
        Elements spuEles = doc.select("div#J_goodsList > ul > li");
        for (Element spuEle : spuEles){
            //获取spu
            long spu = Long.parseLong(spuEle.attr("data-spu"));

            //获取sku信息
            Elements skuEles = spuEle.select("li.ps-item");
            for(Element skuEle : skuEles){
                //获取sku
                long sku = Long.parseLong(skuEle.select("[data-sku]").attr("data-sku"));

                //根据sku查询商品数据
                Item item = new Item();
                item.setSku(sku);
                List<Item> list = this.itemService.findAll(item);
                if(list.size() > 0){
                    //如果商品存在则进行下一轮循环遍历
                    continue;
                }
                //设置商品的spu
                item.setSpu(spu);
                //获取商品的详情url
                String itemUrl = "https://item.jd.com/"+sku+".html";
                item.setUrl(itemUrl);


                String picUrl = "http:" + skuEle.select("img[data-sku]").first().attr("data-lazy-img");
                picUrl = picUrl.replace("/n9/","/n1/");
                String picName = this.httpClientUtils.doGetImage(picUrl);
                item.setPic(picName);


                String priceJson = this.httpClientUtils.doGetHtml("https://p.3.cn/prices/mgets?skuIds=J_" + sku);
                double price = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
                item.setPrice(price);


                String itemInfo = this.httpClientUtils.doGetHtml(item.getUrl());
                String title = Jsoup.parse(itemInfo).select("div.sku-name").text();
                item.setTitle(title);


                item.setCreated(new Date());
                item.setUpdated(item.getCreated());

                this.itemService.save(item);

            }




        }


    }

}
