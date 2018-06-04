package com.hong.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BlogProperties
 * @Description: (自定义properties 属性值映射类)
 * @author hong
 * @date 2017/4/19
 * @version v1.1
 */
/** 使用@Component 注解为spring 管理的类，那么在别的类才可以进行注入使用。**/
@Component
/**
  当我们不是使用的默认的application.properties文件时，需要locations指定了我们要使用的配置文件路径和名称，
  可以这么定义：ConfigurationProperties(locations="classpath:config/company.properties")。
  1.5.x 不再这么支持，请使用
 @PropertySource(ignoreResourceNotFound = true, value = "classpath:conf/application.properties")
 **/
public class ConfigProperties {

    @NotNull
    @Value("${com.hong.title}")
    private String name;

    @Value("${com.hong.text}")
    private String text;

    @Value("${com.hong.value}")
    private String value;
    @Value("${com.hong.number}")
    private Integer number;
    @Value("${com.hong.bignumber}")
    private Long bignumber;
    @Value("${com.hong.test1}")
    private Integer test1;
    @Value("${com.hong.test2}")
    private Integer test2;

    /**
     *  ${key:defaultVlaue} 表示在对应key 不成在时的默认值
     */
    @Value("${tt:test}")
    private String tt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getBignumber() {
        return bignumber;
    }

    public void setBignumber(Long bignumber) {
        this.bignumber = bignumber;
    }

    public Integer getTest1() {
        return test1;
    }

    public void setTest1(Integer test1) {
        this.test1 = test1;
    }

    public Integer getTest2() {
        return test2;
    }

    public void setTest2(Integer test2) {
        this.test2 = test2;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
