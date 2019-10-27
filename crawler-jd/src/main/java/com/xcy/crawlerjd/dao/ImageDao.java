package com.xcy.crawlerjd.dao;

import com.xcy.crawlerjd.pojo.Image;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/1 18:20
 * @Version v1.0
 */
public interface ImageDao extends JpaRepository<Image, Long> {

    int findByTitle(@Param("title")String title);

    //@Query("update image set flag=:flag where id=:id")
    //void updateFlag(@Param("flag") int myFlag,@Param("id") Long id);

    //Page<Image> findBookNoCriteria(Pageable pageable);

}
