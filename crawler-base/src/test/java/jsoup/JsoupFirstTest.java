package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Set;

/**
 * @author xcy
 * @Desc
 * @date 2019/5/19 18:08
 * @Version v1.0
 */
public class JsoupFirstTest {

    @Test
    //通过URL获取html
    public void testUrl() throws Exception {
        //解析url地址,第一个参数是访问url,第二个参数是访问时候的超时时间
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);
        System.out.println(document.html());
        //使用标签选择器,获取title标签中的内容
        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    //通过String获取html
    public void testString() throws Exception {
        String content = FileUtils.readFileToString(new File("C:\\Users\\xcy\\Desktop\\test.html"), "utf8");
        Document document = Jsoup.parse(content);
        //使用标签选择器,获取title标签中的内容
        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    //通过文件获取html
    public void testFile() throws Exception {
        Document document = Jsoup.parse(new File("C:\\Users\\xcy\\Desktop\\test.html"), "utf8");
        //使用标签选择器,获取title标签中的内容
        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testDom() throws Exception {
        Document document = Jsoup.parse(new File("C:\\Users\\xcy\\Desktop\\test.html"), "utf8");

        //根据id查询元素getElementById
        String byIdText = document.getElementById("byId").text();
        System.out.println("根据id查询元素getElementById: " + byIdText);
        //根据标签获取元素getElementsByTag
        String spanText = document.getElementsByTag("p").first().text();
        System.out.println("根据标签获取元素getElementsByTag: " + spanText);

        //根据class获取元素getElementsByClass
        String classText = document.getElementsByClass("class_a class_b").first().html();
        System.out.println("根据class获取元素getElementsByClass: " + classText);

        //根据属性获取元素getElementsByAttribute
        String byAttributeText = document.getElementsByAttribute("abc").first().text();
        System.out.println("根据属性获取元素getElementsByAttribute: " + byAttributeText);

        //根据属性名和属性值 获取元素getElementsByAttribute(key,value)
        String keyValuetext = document.getElementsByAttributeValue("abc", "456").first().text();
        System.out.println("根据属性名和属性值获取: " + keyValuetext);
    }

    @Test
    public void testData() throws Exception {
        Document document = Jsoup.parse(new File("C:\\Users\\xcy\\Desktop\\test.html"), "utf8");
        Element element = document.getElementById("testData");

        //从元素中获取id
        String idText = element.id();
        System.out.println("获取到的id: " + idText);

        //从元素中获取className
        String classNameText = element.className();
        System.out.println("获取到的className: " + classNameText);
        Set<String> classNames = element.classNames();
        for (String s : classNames) {
            System.out.println(s);
        }

        //从元素中获取属性的值attr
        String attrText = element.attr("class");
        System.out.println("通过属性名获取到的属性值: " + attrText);

        //从元素中获取所有属性attributes
        Attributes attributes = element.attributes();
        for (Attribute a : attributes) {
            System.out.println(a.toString());
        }

        //从元素中获取文本内容
        String text = element.text();
        System.out.println("获取到的文本: " + text);
    }

}
