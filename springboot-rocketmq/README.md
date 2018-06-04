
# Spring Boot 整合RocketMQ 需要注意的点：
>1.linux 上安装RocketMQ 可直接参考官网：http://rocketmq.incubator.apache.org/docs/quick-start/

>2.在启动服务时，先启动namesrv 服务（rocketmq从3.x 开始由zookeeper 转向namesrv,更加轻量级），再启动broker 服务

>3.一个应用尽可能用一个 Topic，消息子类型用 tags 来标识，tags 可以由应用自由设置。只有収送消息设置了
  tags，消费方在订阅消息时，才可以利用 tags 在 broker 做消息过滤。