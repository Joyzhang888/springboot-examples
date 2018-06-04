package com.hong.config;

import com.hong.domain.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName: RedisConfig
 * @Description: ( 配置针对User的RedisTemplate实例 )
 * @author
 * @date 2017/5/2
 * @version v1.1
 */
/**
 *   其实我感觉是这样的，1.5.x 和 1.3.x 改动比较大：
 *   测试时，可能遇到以下坑：
 *   1.pom 需要手动指定版本
 *   2.使用redis 默认已支持存储对象，不要再手动处理
 *   3.如果使用JedisConnectionFactory ，需要将application.properties 中的host 改为hostName ，并
 *   @ConfigurationProperties(prefix = spring.redis)
 *   JedisConnectionFactory jedisConnectionFactory() {}
 *   手动绑定。因为查看源码JedisConnectionFactory ，redis 地址属性为hostName;
 */
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis")
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConnectionFactory= new JedisConnectionFactory();
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, User> template = new RedisTemplate<String, User>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new RedisObjectSerializer());
//        return template;
//    }
//
//}
