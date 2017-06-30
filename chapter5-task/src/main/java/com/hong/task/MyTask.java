package com.hong.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * 自定义任务类.
 * Created by hong on 2017/6/30.
 */
@Configuration
@EnableScheduling
public class MyTask {


    /**
     * 这个方法每10秒打印一次.
     * 使用cron表达式 指定：秒，分钟，小时，日期，月份，星期，年（可选）.
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void test(){
        System.out.println("mytask......"+new Date());
    }


}
