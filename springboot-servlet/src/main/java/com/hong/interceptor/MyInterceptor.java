package com.hong.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器.
 * Created by hong on 2017/5/25.
 */
public class MyInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *  依赖于web框架，在SpringMVC中就是依赖于SpringMVC框架。在实现上基于Java的反射机制，属于面向切面编程（AOP）的一种运用。
     *  由于拦截器是基于web框架的调用，因此可以使用spring的依赖注入（DI）进行一些业务操作，同时一个拦截器实例在一个controller生命周期之内可以多次调用。
     *  但是缺点是只能对controller请求进行拦截，对其他的一些比如直接访问静态资源的请求则没办法进行拦截处理
     *
     *  常见应用场景
     *  1、日志记录：记录请求信息的日志，以便进行信息监控、信息统计、计算PV（Page View）等。
     *  2、权限检查：如登录检测，进入处理器检测检测是否登录，如果没有直接返回到登录页面；
     *  3、性能监控：有时候系统在某段时间莫名其妙的慢，可以通过拦截器在进入处理器之前记录开始时间，在处理完后记录结束时间，从而得到该请求的处理时间（如果有反向代理，如apache可以自动记录）；
     *  4、通用行为：读取cookie得到用户信息并将用户对象放入请求，从而方便后续流程使用，还有如提取Locale、Theme信息等，只要是多个处理器都需要的即可使用拦截器实现。
     *  5、OpenSessionInView：如Hibernate，在进入处理器打开Session，在完成后关闭Session。
     *  …………本质也是AOP（面向切面编程），也就是说符合横切关注点的所有功能都可以放入拦截器实现
     *
     *  spring boot拦截器默认主要有
     *    - HandlerInterceptorAdapter
     *    - AbstractHandlerMapping
     *    - UserRoleAuthorizationInterceptor
     *    - LocaleChangeInterceptor
     *    - ThemeChangeInterceptor
     *  拦截器适配器
     *    - HandlerInterceptorAdapter
     */


    /**
     * 预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器（如我们上一章的Controller实现）；
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return true表示继续流程
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
          logger.info("MyInterceptor......afterCompletion()!");
    }


}
