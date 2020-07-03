package com.xcy.es.test;

import com.xcy.es.pojo.Item;
import com.xcy.es.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2020/7/2 15:23
 * @Version v1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ESTest {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    //创建索引和映射
    @Test
    public void createIndex() {
        this.elasticsearchTemplate.createIndex(Item.class);
        this.elasticsearchTemplate.putMapping(Item.class);
    }

    //新增
    @Test
    public void testSave() {
        Item item = new Item();
        item.setId(100);
        item.setTitle("SpringData ES");
        item.setContent("使用SpringData ES完成搜索功能.");
        this.itemService.save(item);
    }

    //修改,和新增的代码是一样的,如果id存在就是修改,如果id不存在则是新增
    @Test
    public void testUpdate() {
        Item item = new Item();
        item.setId(100);
        item.setTitle("SpringData ES");
        item.setContent("使用SpringData ES完成job搜索功能.");
        this.itemService.save(item);
    }

    //删除
    @Test
    public void testDelete() {
        Item item = new Item();
        item.setId(100);
        this.itemService.delete(item);
    }

    //批量保存
    @Test
    public void testSaveAll() {
        List<Item> itemList = new ArrayList<Item>();
        for (int i = 0; i < 100; i++) {
            Item item = new Item();
            item.setId(i);
            item.setTitle("SpringData ES " + i);
            item.setContent("使用SpringData ES完成job搜索功能." + i);
            itemList.add(item);
        }
        this.itemService.saveAll(itemList);
    }

    //查询所有数据
    @Test
    public void testFindAll() {
        Iterable<Item> items = this.itemService.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    //分页查询数据
    @Test
    public void testFindByPage() {
        Page<Item> page = this.itemService.findByPage(1, 10);
        for (Item item : page.getContent()) {
            System.out.println(item);
        }
    }

    //复杂查询
    //根据title和content进行查询,交集
    @Test
    public void testFindByTitleAndContent() {
        //貌似查询出来的数据如果多于10条结果也只有10条?why?
        List<Item> list = this.itemService.findByTitleAndContent("ES", "job");
        for (Item item : list) {
            System.out.println(item);
        }
    }


    //分页 根据title和content进行分页查询,并集
    @Test
    public void testFindByTitleOrContent() {
        Page<Item> page = this.itemService.findByTitleOrContent("22", "23", 1, 5);
        for (Item item : page.getContent()) {
            System.out.println(item);
        }
    }

    //分页 根据title  和 content 和 id范围进行分页查询
    @Test
    public void testFindByTitleAndContentAndIdBetween() {
        Page<Item> page = this.itemService.findByTitleAndContentAndIdBetween("ES", "job", 10, 16, 1, 5);
        for (Item item : page.getContent()) {
            System.out.println(item);
        }
    }
}
