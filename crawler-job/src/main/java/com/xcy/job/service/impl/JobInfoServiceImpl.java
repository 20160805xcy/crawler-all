package com.xcy.job.service.impl;

import com.xcy.job.dao.JobInfoDao;
import com.xcy.job.pojo.JobInfo;
import com.xcy.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/27 23:41
 * @Version v1.0
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobInfo) {
        //判断数据库是否已经存在,存在则更新,不存在则新增
        //根据url和发布时间查询数据,如果为空则表示不存在或者更新了,需要进行新增和更新数据库
        JobInfo param = new JobInfo();
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());

        //执行查询
        List<JobInfo> list = findJobInfo(jobInfo);

        if (list.size() == 0) {
            //如果为空则表示不存在或者更新了,需要进行新增和更新数据库
            jobInfoDao.saveAndFlush(jobInfo);
        }

    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {
        //设置查询条件
        Example<JobInfo> example = Example.of(jobInfo);
        List<JobInfo> list = jobInfoDao.findAll(example);
        return list;
    }

    @Override
    public Page<JobInfo> findJobInfoByPage(int page, int rows) {
        Page<JobInfo> jobInfos = this.jobInfoDao.findAll(PageRequest.of(page - 1, rows));
        return jobInfos;
    }
}
