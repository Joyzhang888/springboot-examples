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
     * @Scheduled 详细参数说明：
     *
     * 1.corn    指定：秒，分钟，小时，日期，月份，星期，年（可选）
     * 2.fixedDelay   从上一个任务完成到下一个任务开始的间隔，单位是毫秒
     * 3.fixedDelayString  同上,只是字符串类型.
     * 4.fixedRate    从上一个任务开始到下一个任务开始的间隔，单位是毫秒
     * 5.fixedRateString  同上,只是字符串类型.
     * 6.initialDelay   任务第一次执行前需要延迟的毫秒数
     * 7.initialDelayString   同上,只是字符串类型.
     * 8.zone    指定时区
     *
     */


    /**
     * 这个方法每10秒打印一次.
     * 使用cron表达式 指定：秒，分钟，小时，日期，月份，星期，年（可选）.
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void test(){
        System.out.println("test......"+new Date().getTime());
    }


    /**
     * 每隔6秒执行.
     */
    @Scheduled(fixedDelay = 6000)
    public void test2(){
        System.out.println("test2..."+new Date().getTime());
    }

//    @Scheduled(fixedDelayString = "5000")
//    public void test3(){
//        System.out.println("test3..."+new Date().getTime());
//    }





}
