package com.hong.jwt.web;

import com.alibaba.fastjson.JSONObject;
import com.hong.jwt.neum.UserInfoEnum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hong on 2017/6/12.
 */
@RestController
public class UserController {


    @PostMapping("/user")
    public JSONObject user(int id){
        JSONObject resultMsg =new JSONObject();
        switch (id) {
            case 1:
               resultMsg.put("username",UserInfoEnum.TEST.getUsername());
                break;
            case 2:
                resultMsg.put("username",UserInfoEnum.ADMIN.getUsername());
                break;
            case 3:
                resultMsg.put("username",UserInfoEnum.ZHANGSAN.getUsername());
                break;
        }

        return resultMsg;
    }
}
