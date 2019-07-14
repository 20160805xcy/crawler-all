package com.xcy.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author xcy
 * @Desc HttpClient发起get携带参数的请求
 * @date 2019/5/19 13:26
 * @Version v1.0
 */
public class HttpGetWithParamTest {
    public static void main(String[] args) throws URISyntaxException {
        //1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置请求地址是:http://yun.itheima.com/course?keys=Java
        //创建URIBuilder
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/course");
        uriBuilder.setParameter("keys","Java");
        //如果多个参数需要设置,则继续调用setParameter方法,链式编程.


        //2.输入网址,发起get请求创建HttpGet对象
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        System.out.println("发起请求的信息: "+httpGet);
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
