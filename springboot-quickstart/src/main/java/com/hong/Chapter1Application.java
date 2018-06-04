package com.hong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 *  SpringBoot 启动类.
 *
 *  继承SpringBootServletInitializer 类，覆盖configure()，把启动类Chapter1Application 注册进去。
 *	外部web应用服务器构建Web Application Context的时候，会把启动类添加进去。
 */
@SpringBootApplication
public class Chapter1Application { //extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Chapter1Application.class, args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(Chapter1Application.class);
//	}
}
