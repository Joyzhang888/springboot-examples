package com.hong.web;

import com.hong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.hong.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hong on 2017/4/25.
 */
@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserService userService;

    @RequestMapping("/getUserById")
    @ResponseBody
    public User getUserById(long id){
         logger.info(userService.getXUserById(id).toString());
         return  userService.getUserById(id);
    }

    @RequestMapping("/add")
    @ResponseBody
    public int add(){
        User user =new User();
        user.setName("李四");
        user.setAge(22);
        return  userService.add(user);
    }

    @RequestMapping("/update")
    @ResponseBody
    public int update(){
        User user =new User();
        user.setId(1L);
        user.setAge(88);
        user.setName("张三丰");
        return  userService.update(user);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int delete(Long id){
        return  userService.delete(id);
    }

}
