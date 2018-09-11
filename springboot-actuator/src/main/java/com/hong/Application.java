package com.hong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tianhong
 * @Description  spring boot 启动类.
 * @date 2018/9/11 11:32
 * @Copyright (c) 2018, DaChen All Rights Reserved.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplication().run(Application.class,args);
    }
}


