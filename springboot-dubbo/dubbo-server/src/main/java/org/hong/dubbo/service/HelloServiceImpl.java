package org.hong.dubbo.service;


import com.alibaba.dubbo.config.annotation.Service;

/**
 * @Description: (dubbo 服务提供.)
 * @author hong
 * @date 2017/10/10
 * @version v1.1
 */
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    @Override
    public String   hello(String msg) {
        return "hello springboot: "+msg;
    }
}
