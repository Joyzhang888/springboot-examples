package com.hong.web;

import com.hong.common.util.PwdUtil;
import com.hong.domain.User;
import com.hong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by hong on 2017/5/6.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;


    @RequestMapping("/user")
    @ResponseBody
    private int registerUser(@Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return bindingResult.getErrorCount();
        }

        //注册时对密码加密 ,使用md5 1024次 和盐值username 加密
        user.setPassword(PwdUtil.createMD5Credentials(user.getPassword(),user.getUsername()).toString());
        return userService.save(user);
    }
}
