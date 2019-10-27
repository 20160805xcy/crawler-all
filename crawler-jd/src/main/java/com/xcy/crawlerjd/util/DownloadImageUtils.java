package com.xcy.crawlerjd.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/4 13:23
 * @Version v1.0
 */
public class DownloadImageUtils {


    public static void download(String urlString, String title, String savePath) throws Exception {

        System.out.println("进来了下载..." + urlString + " -- "+title);
        // 构造URL
        URL url = new URL(urlString);


        // 打开连接
        URLConnection con = url.openConnection();


        //设置请求超时为5s
        con.setConnectTimeout(5 * 1000);

        con.setConnectTimeout(50000);
        con.setReadTimeout(50000);

        con.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");

        //con.connect();


        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        // 获取图片的扩展名
        String extensionName = urlString.substring(urlString.lastIndexOf(".") + 1);
        // 新的图片文件名 = 编号 +"."图片扩展名

        //当前时间字符串
        String format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());

        //创建图片名,重命名图片
        String picName = title + "--" + format + "." + extensionName;

        OutputStream os = new FileOutputStream(sf.getPath() + "\\" + picName);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

}
