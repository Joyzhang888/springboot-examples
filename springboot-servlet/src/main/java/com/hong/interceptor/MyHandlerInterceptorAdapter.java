package com.hong.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器适配器. (相比实现拦截器接口好处在于，我可以选择的重写他的方法，而不用所有的都实现.)
 * Created by hong on 2017/5/25.
 */
public class MyHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("MyHandlerInterceptorAdapter......afterCompletion()!");
        super.afterCompletion(request, response, handler, ex);
    }
}
