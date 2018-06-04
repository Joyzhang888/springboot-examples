package com.hong;

import com.hong.filter.MyFilter;
import com.hong.listener.MyServletContextListener;
import com.hong.servlet.MyServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContextListener;

/**
 * Created by hong on 2017/5/25.
 */
@SpringBootApplication
/* 允许使用@WebServlet、@WebFilter、@WebListener 注解自动注册,推荐使用这一种. */
@ServletComponentScan
public class Application {

    /*
    当使用spring-Boot时，嵌入式Servlet容器通过扫描注解的方式注册Servlet、Filter和Servlet规范的所有监听器（如HttpSessionListener监听器）。
    Spring boot 的主 Servlet 为 DispatcherServlet，其默认的url-pattern为“/”。也许我们在应用中还需要定义更多的Servlet，该如何使用SpringBoot来完成呢？

    主要有两种方式：
    一、代码注册通过ServletRegistrationBean、 FilterRegistrationBean 和 ServletListenerRegistrationBean 获得控制。 也可以通过实现 ServletContextInitializer 接口直接注册。

    二、在 SpringBootApplication 上使用@ServletComponentScan 注解后，Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码。

    */

//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        return new ServletRegistrationBean(new MyServlet(), "/myServlet/*");
//    }

//    @Bean
//    public FilterRegistrationBean filterRegistrationBean(ServletRegistrationBean servletRegistrationBean){
//        return new FilterRegistrationBean(new MyFilter(),servletRegistrationBean);
//    }

//    @Bean
//    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
//        return new ServletListenerRegistrationBean(new MyServletContextListener());
//    }

//    /**
//     * 修改DispatcherServlet默认配置
//     * @param dispatcherServlet
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
//        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
//        registration.getUrlMappings().clear();
//        registration.addUrlMappings("*.do");
//        registration.addUrlMappings("*.json");
//        return registration;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
