package com.xcy.job.controller;

import com.xcy.job.pojo.JobResult;
import com.xcy.job.service.JobRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xcy
 * @Desc
 * @date 2020/7/3 15:20
 * @Version v1.0
 */
@RestController
public class SearchController {

    @Autowired
    private JobRepositoryService jobRepositoryService;

    //salary: *-*
    //page: 1
    //jobaddr: 北京
    //keyword: java
    //Request URL: http://127.0.0.1:8866/search
    //Request Method: POST

    /**
     * 根据条件分页查询招聘信息
     * @param salary
     * @param jobaddr
     * @param keyword
     * @param page
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public JobResult search(String salary, String jobaddr, String keyword, Integer page) {
        JobResult jobResult = this.jobRepositoryService.search(salary, jobaddr, keyword, page);
        return jobResult;
    }


}
