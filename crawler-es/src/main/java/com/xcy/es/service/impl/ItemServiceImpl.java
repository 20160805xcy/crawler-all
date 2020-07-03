package com.xcy.es.service.impl;

import com.xcy.es.dao.ItemRepository;
import com.xcy.es.pojo.Item;
import com.xcy.es.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2020/7/2 15:12
 * @Version v1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void save(Item item) {
        this.itemRepository.save(item);
    }

    public void delete(Item item) {
        this.itemRepository.delete(item);
    }

    public void saveAll(List<Item> itemList) {
        this.itemRepository.saveAll(itemList);
    }

    public Iterable<Item> findAll() {
        Iterable<Item> items = this.itemRepository.findAll();
        return items;
    }

    public Page<Item> findByPage(int page, int rows) {
        Page<Item> items = this.itemRepository.findAll(PageRequest.of(page, rows));
        return items;
    }

    public List<Item> findByTitleAndContent(String title, String content) {
        List<Item> list = this.itemRepository.findByTitleAndContent(title, content);
        return list;
    }

    public Page<Item> findByTitleOrContent(String title, String content, int page, int rows) {
        Page<Item> items = this.itemRepository.findByTitleOrContent(title, content, PageRequest.of(page - 1, rows));
        return items;
    }

    public Page<Item> findByTitleAndContentAndIdBetween(String title, String content, int min, int max, int page, int rows) {
        Page<Item> items = this.itemRepository.findByTitleAndContentAndIdBetween(title, content, min, max, PageRequest.of(page - 1, rows));
        return items;
    }
}
