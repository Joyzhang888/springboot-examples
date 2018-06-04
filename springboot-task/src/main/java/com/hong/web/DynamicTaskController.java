package com.hong.web;

import com.hong.task.DynamicTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hong on 2017/6/30.
 */
@RestController
public class DynamicTaskController {

    @Autowired
    private DynamicTask dynamicTask;

    private static final String DEFAULT_CRON = "0/10 * * * * ?";

    /**
     * 动态修改定时任务.
     * @return
     */
    @GetMapping("/dynamicTask")
    public String dynamicTask(){
        dynamicTask.setCron(DEFAULT_CRON);
        return DEFAULT_CRON;
    }
}
