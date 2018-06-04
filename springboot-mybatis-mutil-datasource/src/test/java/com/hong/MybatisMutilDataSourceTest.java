package com.hong;

import com.hong.domain.Demo;
import com.hong.domain.User;
import com.hong.mapper.master.UserMapper;
import com.hong.mapper.slave.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by hong on 2017/5/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MybatisMutilDataSourceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void test() {
        List<User> users = userMapper.findAll();
        users.forEach(user -> logger.info(user.toString()));

        List<Demo> demos = demoMapper.findAll();
        demos.forEach(demo -> logger.info(demo.toString()));

    }
}
