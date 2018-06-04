package com.hong.web;

import com.hong.localMessage.LocaleMessageSourceUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @author hong
 * @version v1.1
 * @ClassName: ChangeSessionLanauageController
 * @Description: (测试切换当前环境语言)
 * @date 2017/4/22
 */
@Controller
public class ChangeSessionLanauageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LocaleMessageSourceUtil localeMessageSourceUtil;

    /**
     * 当前会话的区域进行切换
     * @param request
     * @param lang
     * @return
     */
    @RequestMapping("/changeLocal")
    public String changeLanauage(HttpServletRequest request, HttpServletResponse response, String lang) {

        String welcome=localeMessageSourceUtil.getMessage("welcome");
        logger.info("title:...."+welcome);

        if(!StringUtils.isEmpty(lang)) {
            //只针对会话的设置
            // if ("zh".equals(lang)) {
            //     request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("zh", "CN"));
            // } else if("en".equals(lang)) {
            //     request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale("en", "US"));
            // }

            //同时作用于会话和Cookie
            //获取当前使用的区域解析器LocaleResolver
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

            //setLocale 设置即可，这样的代码就是不管是会话还是cookie区域解析器都是一样的代码了
            if("zh".equals(lang)){
                localeResolver.setLocale(request, response, new Locale("zh", "CN"));
            }else if("en".equals(lang)){
                localeResolver.setLocale(request, response, new Locale("en", "US"));
            }
        }

        return "changeSessionLanauage";
    }
}
