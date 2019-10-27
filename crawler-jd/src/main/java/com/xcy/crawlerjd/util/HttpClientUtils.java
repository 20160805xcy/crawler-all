package com.xcy.crawlerjd.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author xcy
 * @Desc
 * @date 2019/7/14 15:07
 * @Version v1.0
 */
@Component
public class HttpClientUtils {

    final static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    //连接池管理器
    private PoolingHttpClientConnectionManager cm;

    public HttpClientUtils() {
        this.cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        this.cm.setMaxTotal(100);

        //设置每个主机的最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }

    /**
     * 根据请求地址下载页面数据
     * @param url
     * @return 页面数据
     */
    public String doGetHtml(String url) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");

        //设置请求信息
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                if (response.getEntity() != null) {
                    String content = EntityUtils.toString(response.getEntity());
                    return content;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //没有数据或者出错时,直接返回空串
        return "";
    }

    /**
     * 下载图片
     * @param url
     * @return 图片名称
     */
    public String doGetImage(String url) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        HttpGet httpGet = new HttpGet(url);

        //设置请求信息
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                if (response.getEntity() != null) {

                    //下载图片
                    //获取图片后缀
                    String extName = url.substring(url.lastIndexOf("."));

                    //创建图片名,重命名图片
                    String picName = UUID.randomUUID().toString() + extName;

                    //下载图片
                    OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\xcy\\Desktop\\images\\" + picName));
                    response.getEntity().writeTo(outputStream);

                    //返回图片名称
                    return picName;

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //没有数据或者出错时,直接返回空串
        return "";
    }


    /**
     * 下载图片
     * @param url
     * @return 图片名称
     */
    public String doGetImage222(Long imageId, String url, String title) {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        HttpGet httpGet = new HttpGet(url);

        //设置请求信息
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                if (response.getEntity() != null) {

                    //下载图片
                    //获取图片后缀
                    String extName = url.substring(url.lastIndexOf("."));

                    //当前时间字符串
                    String format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());

                    //创建图片名,重命名图片
                    String picName = title + "--" + format + extName;

                    //下载图片
                    OutputStream outputStream = new FileOutputStream(new File("H:\\images3\\" + picName));
                    response.getEntity().writeTo(outputStream);

                    //返回图片名称
                    return title;

                }
            }

        } catch (IOException e) {
            logger.error("id为 " + imageId + " 下载失败!!! 标题为: " + title);
            e.printStackTrace();
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //没有数据或者出错时,直接返回空串
        return "";
    }


    /**
     * 设置请求的信息
     * @return
     */
    private RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000) //创建连接的最长时间
                .setConnectionRequestTimeout(500) //获取连接的最长时间
                .setSocketTimeout(10000) //数据传输的最长时间
                .build();
        return config;
    }


}
