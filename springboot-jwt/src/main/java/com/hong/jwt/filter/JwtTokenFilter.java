package com.hong.jwt.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hong.jwt.domain.LoginInfo;
import com.hong.jwt.neum.UserInfoEnum;
import com.hong.jwt.utils.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hong on 2017/6/12.
 */
@WebFilter(filterName = "jwtTokenFilter", urlPatterns = {"/oauth/user"})
public class JwtTokenFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        // String authToken = httpRequest.getHeader("Authorization");

        //从cookie 中获取jwt token
        Cookie[] cookies = httpRequest.getCookies();
        String authToken =null;
        for (Cookie cookie : cookies) {
            if("token".equals(cookie.getName())){
                authToken = cookie.getValue();
            }
        }
        String username = jwtHelper.getUsernameFromToken(authToken);

        logger.info("checking authentication für user " + username);

        if (username != null) {

            // 这一步通常去数据库获取用户信息
            LoginInfo loginInfo =new LoginInfo();
            switch (username) {
                case "admin":
                    loginInfo.setId(UserInfoEnum.ADMIN.getId());
                    loginInfo.setUsername(UserInfoEnum.ADMIN.getUsername());
                    loginInfo.setPassword(UserInfoEnum.ADMIN.getPassword());
                    break;
                case "test":
                    loginInfo.setId(UserInfoEnum.TEST.getId());
                    loginInfo.setUsername(UserInfoEnum.TEST.getUsername());
                    loginInfo.setPassword(UserInfoEnum.TEST.getPassword());
                    break;
                case "zhangsan":
                    loginInfo.setId(UserInfoEnum.ZHANGSAN.getId());
                    loginInfo.setUsername(UserInfoEnum.ZHANGSAN.getUsername());
                    loginInfo.setPassword(UserInfoEnum.ZHANGSAN.getPassword());
                    break;
            }

            // 验证 token 有效性.
            if (jwtHelper.validateToken(authToken,loginInfo)) {
               filterChain.doFilter(servletRequest,servletResponse);
            }

            // 验证token 失败.
            resultWrite((HttpServletResponse) servletResponse);
            return;
        }else{
            // 验证token 失败.
            resultWrite((HttpServletResponse) servletResponse);
            return;
        }
    }

    private void resultWrite(HttpServletResponse servletResponse) throws IOException {
        HttpServletResponse httpResponse = servletResponse;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper mapper = new ObjectMapper();
        JSONObject resultMsg =new JSONObject();
        resultMsg.put("result_code", 500);
        resultMsg.put("msg", "authentication failure!");
        httpResponse.getWriter().write(mapper.writeValueAsString(resultMsg));
    }

    @Override
    public void destroy() {

    }
}
