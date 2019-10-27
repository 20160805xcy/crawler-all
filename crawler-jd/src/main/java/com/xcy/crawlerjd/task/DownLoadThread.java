package com.xcy.crawlerjd.task;

import com.xcy.crawlerjd.pojo.Image;
import com.xcy.crawlerjd.util.HttpClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/4 10:39
 * @Version v1.0
 */
public class DownLoadThread extends Thread {

    final static Logger logger = LoggerFactory.getLogger(DownLoadThread.class);

    @Autowired
    HttpClientUtils httpClientUtils;

    private List<Image> imageList;

    public DownLoadThread() {

    }

    public DownLoadThread(List<Image> imageList) {
        this.imageList = imageList;
    }

    @Override
    public void run() {
        for (int i = 0; i < imageList.size(); i++) {
            Image image = imageList.get(i);
            String title = image.getTitle();
            String url = image.getImageLink();
            Long imageId = image.getId();
            try {
                httpClientUtils.doGetImage222(imageId, url, title);
                //成功的话，则更新状态为已下载
                logger.info(imageId + " " + url + " === " + title);
                //imageDao.updateFlag(1, imageId);
            } catch (Exception e) {
                //退出，啥也不干
                logger.error("没下载成功的链接: " + imageId);
            }
        }


    }
}
