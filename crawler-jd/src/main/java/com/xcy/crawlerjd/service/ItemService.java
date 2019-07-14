package com.xcy.crawlerjd.service;

import com.xcy.crawlerjd.pojo.Item;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/7/14 14:44
 * @Version v1.0
 */
public interface ItemService {
    void save(Item item);

    List<Item> findAll(Item item);
}
