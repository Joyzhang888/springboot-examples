package com.hong.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ProfileProperties
 * @Description: (获取多环境下的属性参数)
 * @author hong
 * @date 2017/4/19
 * @version v1.1
 */
@Component
@ConfigurationProperties(prefix = "spring.server")
// 指定对应的配置文件的路径
@PropertySource(ignoreResourceNotFound = true, value = "classpath:conf/${spring.profiles.active}/application-${spring.profiles.active}.properties")
public class ProfileProperties {

    private String address;

    private String port;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
