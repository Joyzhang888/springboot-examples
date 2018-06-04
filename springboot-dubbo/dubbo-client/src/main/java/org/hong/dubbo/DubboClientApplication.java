package org.hong.dubbo;

import org.hong.dubbo.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DubboClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DubboClientApplication.class, args);
        TestService helloService = run.getBean(TestService.class);
        String hello = helloService.hello("spring-boot-starter-dubbo");
        System.out.println(hello);
    }
}
