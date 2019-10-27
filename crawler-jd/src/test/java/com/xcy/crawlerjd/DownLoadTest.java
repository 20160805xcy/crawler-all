package com.xcy.crawlerjd;

import com.xcy.crawlerjd.dao.ImageDao;
import com.xcy.crawlerjd.pojo.Image;
import com.xcy.crawlerjd.service.ImageService;
import com.xcy.crawlerjd.task.DownLoadThread;
import com.xcy.crawlerjd.util.DownloadImageUtils;
import com.xcy.crawlerjd.util.HttpClientUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/2 6:04
 * @Version v1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DownLoadTest {

    final static Logger logger = LoggerFactory.getLogger(DownLoadTest.class);

    @Autowired
    private HttpClientUtils httpClientUtils;

    private static String url;
    private static String title;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    ImageService imageService;


    @Test
    public void download() {

        Page<Image> allImage = imageService.findBookNoCriteria(0, 20);
        System.out.println(allImage);

        //1000-2000
        Page<Image> allImage2 = imageService.findBookNoCriteria(1, 20);
        //2000-3000
        Page<Image> allImage3 = imageService.findBookNoCriteria(2, 20);
        ////3000-4000
        Page<Image> allImage4 = imageService.findBookNoCriteria(3, 20);
        //4000-5000
        Page<Image> allImage5 = imageService.findBookNoCriteria(4, 20);
        //5000-6000
        Page<Image> allImage6 = imageService.findBookNoCriteria(5, 20);
        //6000-7000
        Page<Image> allImage7 = imageService.findBookNoCriteria(6, 20);
        //7000-8000
        Page<Image> allImage8 = imageService.findBookNoCriteria(7, 20);
        //8000-9000
        Page<Image> allImage9 = imageService.findBookNoCriteria(8, 20);
        //9000-10000
        Page<Image> allImage10 = imageService.findBookNoCriteria(9, 20);

        List allList = new ArrayList<Page<Image>>();
        allList.add(allImage);
        allList.add(allImage2);
        allList.add(allImage3);
        allList.add(allImage4);
        allList.add(allImage5);
        allList.add(allImage6);
        allList.add(allImage7);
        allList.add(allImage8);
        allList.add(allImage9);
        allList.add(allImage10);

        ThreadLocal<List> imageList = new ThreadLocal<>();

        for (int i = 0; i < allList.size(); i++) {
            Page<Image> imagePage = (Page<Image>) allList.get(i);


            //try {
            //    DownloadImageUtils.download(image.getImageLink(),image.getTitle(),"H:\\images3\\");
            //} catch (Exception e) {
            //    e.printStackTrace();
            //}

            for (int j = 0; j < imagePage.getSize(); j++) {
                //Thread thread = new ThreadTest(imagePage.getContent());
                imageList.set(imagePage.getContent());
                Thread thread = new ThreadTest(imageList);
                thread.start();
            }

        }

        /**
         Thread threadTest2 = new ThreadTest("二",allImage2.getContent());
         ThreadTest threadTest3 = new ThreadTest("三",allImage3.getContent());
         ThreadTest threadTest4 = new ThreadTest("四",allImage4.getContent());
         ThreadTest threadTest5 = new ThreadTest("五",allImage5.getContent());
         //ThreadTest threadTest6 = new ThreadTest("六",allImage6.getContent());
         //ThreadTest threadTest7 = new ThreadTest("七",allImage7.getContent());
         //ThreadTest threadTest8 = new ThreadTest("八",allImage8.getContent());
         //ThreadTest threadTest9 = new ThreadTest("九",allImage9.getContent());
         //ThreadTest threadTest10 = new ThreadTest("十",allImage10.getContent());
         threadTest2.start();
         threadTest3.start();
         threadTest4.start();
         threadTest5.start();
         //threadTest6.start();
         //threadTest7.start();
         //threadTest8.start();
         //threadTest9.start();
         //threadTest10.start();
         **/


        /**
         ArrayList<Page<Image>> pageArrayList1 = new ArrayList<>();
         pageArrayList1.add(allImage);
         pageArrayList1.add(allImage2);
         ArrayList<Page<Image>> pageArrayList2 = new ArrayList<>();
         pageArrayList2.add(allImage3);
         pageArrayList2.add(allImage4);
         ArrayList<Page<Image>> pageArrayList3 = new ArrayList<>();
         pageArrayList3.add(allImage5);
         pageArrayList3.add(allImage6);
         ArrayList<Page<Image>> pageArrayList4 = new ArrayList<>();
         pageArrayList4.add(allImage7);
         pageArrayList4.add(allImage8);
         ArrayList<Page<Image>> pageArrayList5 = new ArrayList<>();
         pageArrayList5.add(allImage9);
         pageArrayList5.add(allImage10);

         for (int i = 0; i < pageArrayList1.size(); i++) {
         Page<Image> imagePage = pageArrayList1.get(i);
         for (int j = 0; j < 1000; j++) {
         Image image = imagePage.getContent().get(j);

         title = image.getTitle();
         url = image.getImageLink();
         Long imageId = image.getId();
         try {
         httpClientUtils.doGetImage222(imageId ,url, title);
         //成功的话，则更新状态为已下载
         logger.info(imageId + " " + url + " === " + title);
         //imageDao.updateFlag(1, imageId);
         } catch (Exception e) {
         //退出，啥也不干
         logger.error("没下载成功的链接: " + imageId);
         }

         }
         }
         **/


        /**
         List<Image> allImage = imageDao.findAll();
         System.out.println(allImage.size());
         for (int i=0;i<allImage.size();i++){
         Image image = allImage.get(i);
         title = image.getTitle();
         url = image.getImageLink();
         Long imageId = image.getId();
         try {
         String s = httpClientUtils.doGetImage222(url, title);
         //成功的话，则更新状态为已下载
         logger.info(imageId+" "+url+" === "+title);
         //imageDao.updateFlag(1, imageId);

         }catch (Exception e){
         //退出，啥也不干
         logger.error("没下载成功的链接: " + imageId);
         }
         }
         **/

    }


}
