package com.hong.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 动态修改定时任务.
 * Created by hong on 2017/6/30.
 */
@Component
@EnableScheduling
public class DynamicTask implements SchedulingConfigurer {
    /**
     * ① 在定时任务类上增加@EnabledScheduling注解，并实现SchedulingConfigurer接口。
     * ② 设置一个静态的cron，用于存放任务执行周期参数。
     * ③ 开启一个线程，用于模拟实际业务中外部原因修改了任务执行周期。
     * ④ 设置任务触发器，触发任务执行。
     */

    /**
     * 默认cron参数.
     */
    private static final String DEFAULT_CRON = "0/5 * * * * ?";

    /**
     * 动态修改的cron参数.
     */
    private String cron = DEFAULT_CRON;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                new Runnable() {
                    //设置定时任务执行点.
                    @Override
                    public void run() {
                        System.out.println("定时执行的任务." + new Date().getTime());
                    }
                },
                // 设置定时任务.
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        // 定时任务触发，可修改定时任务的执行周期
                        CronTrigger trigger = new CronTrigger(cron);
                        Date nextExecDate = trigger.nextExecutionTime(triggerContext);
                        return nextExecDate;
                    }
                });
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

}
