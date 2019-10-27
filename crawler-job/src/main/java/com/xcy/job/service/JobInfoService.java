package com.xcy.job.service;

import com.xcy.job.pojo.JobInfo;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/27 23:39
 * @Version v1.0
 */
public interface JobInfoService {

    void save(JobInfo jobInfo);

    List<JobInfo> findJobInfo(JobInfo jobInfo);
}
