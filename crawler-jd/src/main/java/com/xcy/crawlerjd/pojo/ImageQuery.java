package com.xcy.crawlerjd.pojo;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/2 11:19
 * @Version v1.0
 */
public class ImageQuery {

    private Long id;
    private String title;
    private String imageLink;

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
}
