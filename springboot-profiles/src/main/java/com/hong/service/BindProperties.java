package com.hong.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: BindProperties
 * @Description: (松散的绑定)
 * @author hong
 * @date 2017/4/19
 * @version v1.1
 */
@Component
@ConfigurationProperties(prefix ="com.hong")
public class BindProperties {

    private Integer ipport;

    private String contextPath;

    public Integer getIpport() {
        return ipport;
    }

    public void setIpport(Integer ipport) {
        this.ipport = ipport;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
