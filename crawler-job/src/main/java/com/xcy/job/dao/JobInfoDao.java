package com.xcy.job.dao;

import com.xcy.job.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/27 23:38
 * @Version v1.0
 */
public interface JobInfoDao extends JpaRepository<JobInfo, Long> {
}
