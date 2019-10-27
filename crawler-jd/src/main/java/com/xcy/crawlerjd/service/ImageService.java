package com.xcy.crawlerjd.service;

import com.xcy.crawlerjd.pojo.Image;
import com.xcy.crawlerjd.pojo.ImageQuery;
import com.xcy.crawlerjd.pojo.Item;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/1 18:20
 * @Version v1.0
 */
public interface ImageService {

    void save(Image image);

    Page<Image> findBookNoCriteria(Integer page, Integer size);


}
