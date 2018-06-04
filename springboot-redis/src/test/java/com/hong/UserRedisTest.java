package com.hong;


import com.hong.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author
 * @version v1.1
 * @ClassName: UserRedisTest
 * @Description: (redis测试存储对象)
 * @date 2017/5/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        ValueOperations<String, User> operations =redisTemplate.opsForValue();
        // 保存对象
        User user = new User("zhangsan", 20);
        operations.set(user.getUsername(), user);
        user = new User("lisi", 30);
        operations.set(user.getUsername(), user);
        user = new User("wangwu", 40);
        operations.set(user.getUsername(), user);
        Assert.assertEquals(20, operations.get("zhangsan").getAge().longValue());
        Assert.assertEquals(30, operations.get("lisi").getAge().longValue());
        Assert.assertEquals(40, operations.get("wangwu").getAge().longValue());

    }

}
