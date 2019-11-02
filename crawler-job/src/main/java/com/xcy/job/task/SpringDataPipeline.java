package com.xcy.job.task;

import com.xcy.job.pojo.JobInfo;
import com.xcy.job.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author xcy
 * @Desc
 * @date 2019/11/2 20:19
 * @Version v1.0
 */
@Component
public class SpringDataPipeline implements Pipeline {

    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的招聘详情对象
        JobInfo jobInfo = resultItems.get("jobInfo");
        //判断数据是否不为空
        if (null != jobInfo) {
            //如果不为空则将数据保存到数据库中
            jobInfoService.save(jobInfo);
        }
    }
}
