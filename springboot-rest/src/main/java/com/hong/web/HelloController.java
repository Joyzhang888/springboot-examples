package com.hong.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloController
 * @Description: (Hello World)
 * @author hong
 * @date 2017/4/20
 * @version v1.1
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return  "hello spring boot";
    }

}
