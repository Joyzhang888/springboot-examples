package com.hong.rocketmq.bean;

import com.hong.rocketmq.config.RocketMQProperties;
import com.hong.rocketmq.event.RocketMQEvent;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义consumer.
 * Created by hong on 2017/5/24.
 */
@Configuration
public class MyConsumer {

    @Autowired
    private RocketMQProperties rocketMQProperties;

    /**
     * '
     * Spring中提供了ApplicationEventPublisher接口作为事件发布者,
     * 并且ApplicationContext实现了这个接口,担当起了事件发布者这一角色。
     */
    @Autowired
    private ApplicationEventPublisher publisher;

    @Bean
    public DefaultMQPushConsumer pushConsumer() throws MQClientException {
        /**
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketMQProperties.getConsumer().getConsumerGroupName());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr(rocketMQProperties.getNamesrvAddr());
        consumer.setInstanceName(rocketMQProperties.getConsumer().getInstanceName());
        consumer.setConsumeMessageBatchMaxSize(1);//设置批量消费，以提升消费吞吐量，默认是1


        /**
         * 订阅指定topic下tags
         */
        consumer.subscribe(rocketMQProperties.getConsumer().getTopic(), rocketMQProperties.getConsumer().getSubscribe());

        consumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {

            MessageExt msg = msgs.get(0);
            try {
                // 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
                System.out.println(Thread.currentThread().getName() + " Receive New Messages: " + msgs.size());

                // 发布消息到达的事件，以便分发到每个tag的监听方法
                this.publisher.publishEvent(new RocketMQEvent(consumer, msg));
                System.out.println("消息到达事件已经发布成功！");
            } catch (Exception e) {
                e.printStackTrace();
                if (msg.getReconsumeTimes() <= 3) {//重复消费3次
                    //TODO 进行日志记录
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                } else {
                    //TODO 消息消费失败，进行日志记录
                }
            }

            //如果没有return success，consumer会重复消费此信息，直到success。
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
         */
        consumer.start();

        return consumer;
    }
}
