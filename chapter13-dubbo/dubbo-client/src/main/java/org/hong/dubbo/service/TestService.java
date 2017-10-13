package org.hong.dubbo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Component
public class TestService {

    @Reference(version = "1.0.0")
    public HelloService helloService;

    public String hello(String msg) {
        return helloService.hello(msg) ;
    }
}
