package com.xcy.job.service;

import com.xcy.job.pojo.JobInfoField;
import com.xcy.job.pojo.JobResult;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2020/7/3 13:20
 * @Version v1.0
 */
public interface JobRepositoryService {

    /**
     * 保存一条数据
     * @param jobInfoField
     */
    void save(JobInfoField jobInfoField);

    /**
     * 批量保存数据
     * @param list
     */
    void saveAll(List<JobInfoField> list);

    /**
     * 根据条件分页查询招聘信息
     * @param salary
     * @param jobaddr
     * @param keyword
     * @param page
     * @return
     */
    JobResult search(String salary, String jobaddr, String keyword, Integer page);
}
