package com.xcy.crawlerjd.pojo;

import javax.persistence.*;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/1 18:18
 * @Version v1.0
 */
@Entity
@Table(name = "image")
@NamedQuery(name = "Image.findByTitle",
        query = "select count (0) from Image t where t.title = ?1")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String imageLink;

    private Integer flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
