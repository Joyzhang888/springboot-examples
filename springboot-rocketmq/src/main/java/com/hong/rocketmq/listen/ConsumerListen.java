package com.hong.rocketmq.listen;

import com.hong.rocketmq.event.RocketMQEvent;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * 自定义消费监听器.
 * Created by hong on 2017/5/24.
 */
@Component
public class ConsumerListen {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener(condition = "#event.topic=='Topic'")
    public void listen(RocketMQEvent event) {
        DefaultMQPushConsumer consumer = event.getConsumer();
        try {
            String msg = new String(event.getMessageExt().getBody(), "utf-8");
            logger.info("消费者获得到的消息:"+msg);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
