package com.xcy.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author xcy
 * @Desc HttpClient发起post请求
 * @date 2019/5/19 13:26
 * @Version v1.0
 */
public class HttpPostTest {
    public static void main(String[] args) {
        //1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址,发起post请求创建HttpPost对象
        HttpPost httpPost = new HttpPost("http://www.itcast.cn");
        CloseableHttpResponse response = null;
        try {
            //3.发起请求,返回响应.
            response = httpClient.execute(httpPost);
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
