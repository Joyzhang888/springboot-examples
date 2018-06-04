package com.hong.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 自定义监听器.
 * Created by hong on 2017/5/25.
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("这一个一个自定义ServletListener......");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
