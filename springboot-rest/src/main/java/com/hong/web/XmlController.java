package com.hong.web;


import com.hong.domain.User;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by hong on 2017/6/24.
 */
@RestController
public class XmlController {

    @Bean
    public User createUser() {
        User user = new User();
        user.setName("zhangsan");
        user.setAge(18);
        user.setCreateTime(new Date());
        return user;
    }

    @Autowired
    @Qualifier("createUser")
    private User user;

    @GetMapping(value = "/userXml",produces = MediaType.APPLICATION_XML_VALUE)

    public User toXml() {

        return user;
    }
}
