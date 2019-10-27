package com.xcy.crawlerjd;

import com.xcy.crawlerjd.pojo.Image;
import com.xcy.crawlerjd.util.DownloadImageUtils;
import com.xcy.crawlerjd.util.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/2 13:32
 * @Version v1.0
 */
public class ThreadTest extends Thread {

    final static Logger logger = LoggerFactory.getLogger(ThreadTest.class);

    @Autowired
    private HttpClientUtils httpClientUtils;

    //private List<Image> imageList;

    private ThreadLocal<List> imageList = new ThreadLocal<>();

    public ThreadTest(ThreadLocal<List> imageList) {
        this.imageList = imageList;
    }

    //public ThreadTest(List<Image> imageList) {
    //    this.imageList = imageList;
    //}


    @Override
    public void run() {

        //for (int i = 0; i < imageList.size(); i++) {
        //    Image image = imageList.get(i);
        //
        //    String title = image.getTitle();
        //    String url = image.getImageLink();
        //    Long imageId = image.getId();
        //    try {
        //        System.out.println(title + " == " + url + " == " + imageId);
        //        //String s = httpClientUtils.doGetImage222(imageId, url, title);
        //
        //        DownloadImageUtils.download(url,title,"H:\\images3\\");
        //
        //        logger.info("线程名称: " + name + " -- " + imageId + " " + url + " === " + title);
        //        //System.out.println("线程名称: "+ name + " -- "+ imageId + " " + url + " === " + title);
        //        //imageDao.updateFlag(1, imageId);
        //
        //    } catch (Exception e) {
        //        //退出，啥也不干
        //        //logger.error("没下载成功的链接: " + imageId);
        //        e.printStackTrace();
        //        System.out.println("没下载成功的链接: " + imageId);
        //    }
        //
        //}
        List<Image> list = imageList.get();
        for(Image image : list){
            try {
                System.out.println(image.getTitle() + " == " + image.getImageLink() + " == " + image.getId());
                //String s = httpClientUtils.doGetImage222(imageId, url, title);

                DownloadImageUtils.download(image.getImageLink(),image.getTitle(),"H:\\images3\\");

                logger.info("线程名称: " + Thread.currentThread().getName() + "---" + image.getImageLink() + " " + image.getTitle() );
                //System.out.println("线程名称: "+ name + " -- "+ imageId + " " + url + " === " + title);
                //imageDao.updateFlag(1, imageId);

            } catch (Exception e) {
                //退出，啥也不干
                //logger.error("没下载成功的链接: " + imageId);
                e.printStackTrace();
                System.out.println("没下载成功的链接: " + image.getId());
            }
        }


    }
}
