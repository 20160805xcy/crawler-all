package com.xcy.job.pojo;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2020/7/3 15:18
 * @Version v1.0
 */
public class JobResult {

    private List<JobInfoField> rows;

    private Integer pageTotal;

    public List<JobInfoField> getRows() {
        return rows;
    }

    public void setRows(List<JobInfoField> rows) {
        this.rows = rows;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
