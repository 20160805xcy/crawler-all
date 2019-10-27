package com.xcy.crawlerjd.service.impl;

import com.xcy.crawlerjd.dao.ImageDao;
import com.xcy.crawlerjd.pojo.Image;
import com.xcy.crawlerjd.pojo.ImageQuery;
import com.xcy.crawlerjd.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/1 18:21
 * @Version v1.0
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public void save(Image image) {
        imageDao.save(image);
    }

    @Override
    public Page<Image> findBookNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        return imageDao.findAll(pageable);
    }


}
