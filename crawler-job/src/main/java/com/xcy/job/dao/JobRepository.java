package com.xcy.job.dao;

import com.xcy.job.pojo.JobInfoField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author xcy
 * @Desc
 * @date 2020/7/3 13:19
 * @Version v1.0
 */
@Component
public interface JobRepository extends ElasticsearchRepository<JobInfoField, Long> {
    Page<JobInfoField> findBySalaryMinBetweenAndSalaryMaxBetweenAndJobAddrAndJobNameAndJobInfo(int salaryMin, int salaryMax, int salaryMin1, int salaryMax1, String jobaddr, String keyword, String keyword1, Pageable pageable);
}
