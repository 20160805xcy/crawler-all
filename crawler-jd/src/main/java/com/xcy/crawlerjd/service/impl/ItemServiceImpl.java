package com.xcy.crawlerjd.service.impl;

import com.xcy.crawlerjd.dao.ItemDao;
import com.xcy.crawlerjd.pojo.Item;
import com.xcy.crawlerjd.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/7/14 14:48
 * @Version v1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public void save(Item item) {
        itemDao.save(item);
    }

    @Override
    public List<Item> findAll(Item item) {
        Example<Item> example = Example.of(item);
        List<Item> list = itemDao.findAll(example);
        return list;
    }
}
