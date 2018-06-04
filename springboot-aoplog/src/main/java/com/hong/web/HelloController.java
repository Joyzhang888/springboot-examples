package com.hong.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hong on 2017/5/19.
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "张三") String name) {
        //假设出现异常
//        int s=1/0;
        if (true) {
            throw new IllegalArgumentException("name参数的长度必须大于3，小于10！");
        }
        return "hello" + name;
    }
}
