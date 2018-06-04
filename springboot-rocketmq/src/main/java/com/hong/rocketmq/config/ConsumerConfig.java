package com.hong.rocketmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Consumer 属性
 * Created by hong on 2017/5/24.
 */
@Component
@ConfigurationProperties(prefix = "spring.extend.rocketmq.consumer")
@PropertySource(ignoreResourceNotFound = true,value = "classpath:rocketmq/rocketmq.properties")
public class ConsumerConfig {

    private String instanceName;
    private String consumerGroupName;
    private String topic;
    private String subscribe;


    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getConsumerGroupName() {
        return consumerGroupName;
    }

    public void setConsumerGroupName(String consumerGroupName) {
        this.consumerGroupName = consumerGroupName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
