package com.hong.web;

import com.hong.domain.primary.User;
import com.hong.domain.primary.UserRepository;
import com.hong.domain.secondary.Demo;
import com.hong.domain.secondary.DemoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by hong on 2017/5/1.
 */
@Controller
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DemoRepository demoRepository;

    @RequestMapping("/hello")
    public String hello(){
        List<User> userList = userRepository.findAll();
        if(!CollectionUtils.isEmpty(userList)) {
            userList.forEach(user -> logger.info(user.toString()));
        }

        List<Demo> demoList =demoRepository.findAll();
        if(!CollectionUtils.isEmpty(demoList)) {
            demoList.forEach(demo -> logger.info(demo.toString()));
        }
        return "success";
    }
}
