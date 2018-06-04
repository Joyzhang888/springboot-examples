package com.hong.common.filter;

import com.hong.common.util.AjaxUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 我们知道Ajax不能做页面redirect和forward跳转，所以Ajax请求假如没登录，
 * 那么这个请求给用户的感觉就是没有任何反应，而用户又不知道用户已经退出或是  Session  超时了。
 * 这个时候如何解决？
 */
public class LoginFilter extends AccessControlFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     *
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

        // 验证是否登录成功 || 是否是配置的LoginUrl url地址
        if (SecurityUtils.getSubject().isAuthenticated() || isLoginRequest(servletRequest, servletResponse)) {
            return true;
        }

        if (AjaxUtil.isAjaxRequest((HttpServletRequest) servletRequest)) {
            Map<String, String> resultMap = new HashMap<>();
            logger.debug("当前用户没有登录，并且是Ajax请求！", LoginFilter.class);
            resultMap.put("login_status", "300");
            resultMap.put("message", "\u5F53\u524D\u7528\u6237\u6CA1\u6709\u767B\u5F55\uFF01");//当前用户没有登录！
            AjaxUtil.out(servletResponse, resultMap);
        }
        return false;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //保存Request和Response 到登录后的链接
        saveRequestAndRedirectToLogin(servletRequest, servletResponse);
        return false;
    }
}
