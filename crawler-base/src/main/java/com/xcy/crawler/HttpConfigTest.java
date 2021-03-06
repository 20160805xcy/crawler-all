package com.xcy.crawler;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author xcy
 * @Desc HttpClient发起get请求,设置配置的请求参数
 * @date 2019/5/19 13:26
 * @Version v1.0
 */
public class HttpConfigTest {
    public static void main(String[] args) {
        //1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址,发起get请求创建HttpGet对象
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        //配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) //设置连接最长时间,单位毫秒
                .setConnectionRequestTimeout(500)  //设置获取连接的最长时间,单位毫秒
                .setSocketTimeout(10 * 1000)  //设置传输数据的最长时间,单位毫秒
                .build();

        httpGet.setConfig(config);

        CloseableHttpResponse response = null;
        try {
            //3.发起请求,返回响应.
            response = httpClient.execute(httpGet);
            //4.解析响应,获取数据.
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //5.关闭response
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            //6.关闭httpClient
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
