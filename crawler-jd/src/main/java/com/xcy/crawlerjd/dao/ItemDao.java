package com.xcy.crawlerjd.dao;

import com.xcy.crawlerjd.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xcy
 * @Desc
 * @date 2019/7/14 14:38
 * @Version v1.0
 */
public interface ItemDao extends JpaRepository<Item, Long> {


}
