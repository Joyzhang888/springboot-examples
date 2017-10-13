package org.hong.dubbo.service;


import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloServiceImpl implements HelloService{

    @Override
    public String hello(String msg) {
        return "spring boot dubbo: "+msg;
    }
}
