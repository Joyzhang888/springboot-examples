package com.hong.rocketmq.bean;

import com.hong.rocketmq.config.RocketMQProperties;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 自定义producer.
 * Created by hong on 2017/5/24.
 */
@Configuration
public class MyProducer {

    @Autowired
    private RocketMQProperties rocketMQProperties;

    /**
     * 初始化 RocketMQ 发送普通消息的生产者
     * @return
     * @throws MQClientException
     */
    @Bean(value = "producer")
    public DefaultMQProducer defaultProducer() throws MQClientException {
        /**
         * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ProducerGroupName需要由应用来保证唯一<br>
         * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
         * 因为服务器会回查这个Group下的任意一个Producer
         */
        DefaultMQProducer producer = new DefaultMQProducer(rocketMQProperties.getProducer().getProducerGroupName());
        producer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
        producer.setInstanceName(rocketMQProperties.getProducer().getInstanceName());
        producer.setVipChannelEnabled(false);
        //如果该条消息在默认时间内没有发送成功，那么重试3次。
        producer.setRetryTimesWhenSendFailed(3);

        /**
         * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         * 注意：切记不可以在每次发送消息时，都调用start方法
         */
        producer.start();
        System.out.println("RocketMq defaultProducer Started.");
        return producer;
    }



}
