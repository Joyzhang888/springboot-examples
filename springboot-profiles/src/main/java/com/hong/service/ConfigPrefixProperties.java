package com.hong.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ConfigPrefixProperties
 * @Description: (针对多个属性自定义属性注入)
 * @author hong
 * @date 2017/4/19
 * @version v1.1
 */
@Component
/** prefix设置key的前缀，避免属性一个一个映射**/
@ConfigurationProperties(prefix ="com.hong")
public class ConfigPrefixProperties {


    private String name;

    private String value;

    private Integer number;

    private Long bignumber;

    private Integer test1;

    private Integer test2;

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
}
