package com.xcy.es.dao;

import com.xcy.es.pojo.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2020/7/2 15:08
 * @Version v1.0
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Integer> {

    List<Item> findByTitleAndContent(String title, String content);

    Page<Item> findByTitleOrContent(String title, String content, Pageable pageable);

    Page<Item> findByTitleAndContentAndIdBetween(String title, String content, int min, int max, Pageable pageable);
}
