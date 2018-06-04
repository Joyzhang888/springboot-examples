package com.hong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hong on 2017/5/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StringRedisTemplateTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public  void test(){
        stringRedisTemplate.opsForValue().set("aaa","aaa111");
        logger.info(stringRedisTemplate.opsForValue().get("aaa"));
    }
}
