package com.xcy.es.service;

import com.xcy.es.pojo.Item;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2020/7/2 15:11
 * @Version v1.0
 */
public interface ItemService {
    void save(Item item);

    void delete(Item item);

    void saveAll(List<Item> itemList);

    Iterable<Item> findAll();

    Page<Item> findByPage(int page, int rows);

    List<Item> findByTitleAndContent(String title, String content);

    Page<Item> findByTitleOrContent(String title, String content, int page, int rows);

    Page<Item> findByTitleAndContentAndIdBetween(String title, String content, int min, int max ,int page, int rows);
}
