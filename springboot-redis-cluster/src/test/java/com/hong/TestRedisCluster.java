package com.hong;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.JedisCluster;

/**
 * Created by hong on 2017/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes ={Application.class})
@WebAppConfiguration
public class TestRedisCluster {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private JedisCluster jedisCluster;


    /**
     * 注: 我们在使用RedisTemplate 时,在不指定<K, V> 具体值时,
     * spring默认采用defaultSerializer = new JdkSerializationRedisSerializer();来对key，value进行序列化操作,
     * 所以这时候redis 的可以 就会出来一堆的\xac\xed\x00\x05t\x00\tb 这种东西;
     *
     * 所以我们可以选择两种处理方法:
     * 1.直接使用RedisTemplate<String,String>  指定。
     * 2.
     *
     */
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    @Test
    public void test(){
        jedisCluster.set("test_jedis_cluster","123456");
        Assert.assertEquals("123456",jedisCluster.get("test_jedis_cluster"));
        String value = jedisCluster.get("test_jedis_cluster");
        logger.info(value);

        redisTemplate.opsForValue().set("kkk","kkk");
        redisTemplate.opsForValue().set("k2","v2");
        redisTemplate.opsForValue().set("k3","v3");
        redisTemplate.opsForValue().set("k4","v4");

        logger.info(redisTemplate.opsForValue().get("kkk"));
        logger.info(redisTemplate.opsForValue().get("test_jedis_cluster"));

    }
}
