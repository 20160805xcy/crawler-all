package com.xcy.crawlerjd;

import com.xcy.crawlerjd.dao.ImageDao;
import com.xcy.crawlerjd.pojo.Image;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/1 16:34
 * @Version v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderTest {

    final static Logger logger = LoggerFactory.getLogger(SpiderTest.class);


    static String url = "https://www.kanjiantu.com/medusa/?page=512&seek=KfadG";
    //static String url = "https://www.kanjiantu.com/medusa/?list=images&sort=views_desc&page=2&seek=2463.Ck0zD";
    static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36";
    static String nextLink;
    static int count = 0;
    static int j;


    @Autowired
    private ImageDao imageDao;

    /**
    @Test
    public void testSaveOne() {
        Image image = new Image();
        image.setImageLink("www.sss.com");
        image.setTitle("hhhh");
        imageDao.save(image);
    }


    @Test
    public void testFindOne() {
        Image img = imageDao.findByTitle("hhhh");
        System.out.println(img);

    }
     **/

    @Test
    public void test01() throws IOException {
        for (int i = 0; i < 1000; i++) {
            if (i != 0) {
                url = nextLink;
            }
            logger.info("正在解析第 " + (i + 1) + " 页( " + url + " )数据");
            go(url, userAgent, imageDao);
        }
    }

    private static void go(String url, String userAgent, ImageDao imageDao) throws IOException {

        Connection connect = Jsoup.connect(url);
        connect.header("user-agent", userAgent);

        Document document = connect.get();
        System.out.println(document+"------->");

        //获取到img所有的个数
        int imgSize = document.getElementsByTag("img").size();
        //获取到trueImg所有的个数
        int trueImgSize = document.getElementsByClass("list-item-desc-title-link").size();
        logger.info("这一页有图片链接: " + imgSize + " 个,其中符合要求的有 " + trueImgSize + " 个");
        j = 0;



        for (int i = 0; i < imgSize; i++) {
            //获取到所有图片的链接地址
            String imgLink = document.getElementsByTag("img").get(i).attr("src");

            if (imgLink.startsWith("https://www.kanjiantu.com/images")) {
            //if (imgLink.startsWith("https://s6tu.com/images/")) {
                String title = "";

                if (j < trueImgSize) {
                    ++j;
                    //获取图片标题
                    //title = document.getElementsByClass("list-item-desc-title-link").get(j - 1).attr("title");
                    title = document.getElementsByClass("list-item-desc-title-link").get(j - 1).text();
                }
                //替换掉.md
                String trueLink = imgLink.replace(".md", "");

                System.out.println("所有的标题============================ "+title);
                // 先查询是否重复
                int titleSize = imageDao.findByTitle(title);
                if (titleSize == 0) {
                    count++;
                    // 再插入数据库
                    Image image = new Image();
                    image.setTitle(title);
                    image.setImageLink(trueLink);
                    imageDao.save(image);
                    logger.info(count + " ==> " + title + " ==> " + trueLink);
                }
            }
        }

        //获取到下一页的链接地址
        nextLink = document.getElementsByClass("pagination-next").select("a").attr("href");
        logger.info("下一页的链接: " + nextLink);
    }

}
