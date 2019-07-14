package com.xcy.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author xcy
 * @Desc 使用连接池来管理httpClient
 * @date 2019/5/19 17:14
 * @Version v1.0
 */
public class HttpClientPoorTest {

    public static void main(String[] args) {
        //创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        cm.setMaxTotal(1000);
        //设置每个主机的最大连接数
        cm.setDefaultMaxPerRoute(10);

        //使用连接池管理器管理发起的请求
        doGet(cm);
        doPost(cm);
    }


    private static void doGet(PoolingHttpClientConnectionManager cm) {
        //不是每次创建新的HttpClient,而是从连接池中获取
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response !=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //不能关闭httpClient,此时是交给了连接池来关闭
                //httpClient.close();
            }
        }


    }

    private static void doPost(PoolingHttpClientConnectionManager cm) {
        //TODO 自己实现,这里主要演示连接池
    }


}
