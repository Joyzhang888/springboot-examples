package com.hong;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * @ClassName: Application
 * @Description: (spring boot 启动类)
 * @author hong
 * @date 2017/4/20
 * @version v1.1
 */
//@Configuration
@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }


    /**
     * 会话区域解析器之SessionLocaleResolver；
     * @return
     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        //设置默认区域  Locale.US  Locale.CHINA ...
//        slr.setDefaultLocale(Locale.CHINA);
//        return slr;
//    }

    /**
     * Cookie区域解析器之CookieLocaleResolver；
     * Cookie区域解析器也就是说，你设置完针对cookie 有效期内生效，session失效。
     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver slr = new CookieLocaleResolver();
//        //设置默认区域,
//        slr.setDefaultLocale(Locale.CHINA);
//        slr.setCookieMaxAge(3600);//设置cookie有效期.
//        return slr;
//    }

    /**
     * 固定的区域解析器之FixedLocaleResolver ；
     *
     * 其实就是不能动态修改,如果尝试修改抛出如下异常:
     * java.lang.UnsupportedOperationException: Cannot change fixed locale - use a different locale resolution strategy
     * @return
     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        FixedLocaleResolver slr = new FixedLocaleResolver ();
//        //设置默认区域,
//        slr.setDefaultLocale(Locale.US);
//        return slr;
//    }

}
