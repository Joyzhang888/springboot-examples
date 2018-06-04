package com.hong.rocketmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Producer 属性
 * Created by hong on 2017/5/24.
 */
@Component
@ConfigurationProperties(prefix = "spring.extend.rocketmq.producer")
@PropertySource(ignoreResourceNotFound = true,value = "classpath:rocketmq/rocketmq.properties")
public class ProducerConfig {

    private String instanceName;
    private String producerGroupName;
    private String topic;
    private List<String> subscribe;


    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getProducerGroupName() {
        return producerGroupName;
    }

    public void setProducerGroupName(String producerGroupName) {
        this.producerGroupName = producerGroupName;
    }

    public List<String> getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(List<String> subscribe) {
        this.subscribe = subscribe;
    }
}
