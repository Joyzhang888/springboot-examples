package org.hong.dubbo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 *  spring boot 启动类.
 */
@SpringBootApplication
@ImportResource(locations={"classpath:spring-dubbo.xml"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
