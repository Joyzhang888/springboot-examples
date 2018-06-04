package com.hong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 *  Spring Boot jsp 启动类
 *  Created by hong on 2017/4/23.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    /**
     *
     *  注：Tomcat支持war的打包方式，spring boot支持war打包方式。Jetty现在不支持JSP嵌入容器。Undertow根本就不支持JSP。
     *      所以答案就是打包成war，jsp会自然按照servlet的标准部署。但也就意味着你不可以用嵌入式的方式运行，而是Tomcat Server + war的部署方式。
     *
     *      推荐两种方式：
     *      1.加入tomcat 中运行。
     *      2.使用spring boot maven 插件 spring-boot-maven-plugin  ，执行maven 命令：spring-boot:run
     *
     *      Spring Boot使用jsp时，比如说css，image，js 等三种静态资源文件 配置到resource/static 下.
     */

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
