package com.hong.rocketmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * RocketMQ 属性
 * Created by hong on 2017/5/23.
 */
@Component
@ConfigurationProperties(prefix ="spring.extend.rocketmq")
@PropertySource(ignoreResourceNotFound = true,value = "classpath:rocketmq/rocketmq.properties")
public class RocketMQProperties {

    private String namesrvAddr;
    private ProducerConfig producer;
    private ConsumerConfig consumer;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }
    public ProducerConfig getProducer() {
        return producer;
    }

    public void setProducer(ProducerConfig producer) {
        this.producer = producer;
    }

    public ConsumerConfig getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerConfig consumer) {
        this.consumer = consumer;
    }
}
