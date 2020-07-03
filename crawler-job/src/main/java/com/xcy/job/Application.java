package com.xcy.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xcy
 * @Desc
 * @date 2019/10/27 23:55
 * @Version v1.0
 */
@SpringBootApplication
//@EnableScheduling//开启定时任务
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
