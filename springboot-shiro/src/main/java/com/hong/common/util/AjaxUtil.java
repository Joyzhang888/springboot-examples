package com.hong.common.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by hong on 2017/5/14.
 */
public class AjaxUtil {

    private static final Logger logger = LoggerFactory.getLogger(AjaxUtil.class);


    /**
     * 判断一个请求是否是ajax 请求
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(header) ? true : false;
        return isAjax;
    }


    /**
     * response 输出JSON
     *
     * @param response
     * @param resultMap
     * @throws IOException
     */
    public static void out(ServletResponse response, Map<String, String> resultMap) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(JSONObject.toJSONString(resultMap).toString());
        } catch (Exception e) {
            logger.error("输出JSON报错.",AjaxUtil.class);
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

}
