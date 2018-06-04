package com.hong.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloController
 * @Description: (helloword test )
 * @author hong
 * @date 2017/4/19
 * @version v1.1
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index(){
        return "hello Spring Boot";
    }

}
