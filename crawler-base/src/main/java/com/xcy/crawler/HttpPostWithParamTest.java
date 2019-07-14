package com.xcy.crawler;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xcy
 * @Desc HttpClient发起携带参数的post的请求
 * @date 2019/5/19 13:26
 * @Version v1.0
 */
public class HttpPostWithParamTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址,发起post请求创建HttpPost对象
        HttpPost httpPost = new HttpPost("http://www.itcast.cn");

        //申明List集合,封装表单中的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //设置参数
        params.add(new BasicNameValuePair("Keys","Java"));
        //创建表单的Entity对象,第一个参数是封装好的表单数据,第二个参数是编码
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf8");

        httpPost.setEntity(formEntity);

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
