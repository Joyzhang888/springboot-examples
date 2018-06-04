package com.hong.client;

import com.hong.domain.User;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hong on 2017/6/24.
 */
public class RestClient {

    public static void main(String[] args) {
        //spring boot 提供的rest 客户端模板.
        RestTemplate restTemplate = new RestTemplate();
        //调用rest 接口.
        User user = restTemplate.getForObject("http://localhost:8080/user", User.class);
        System.out.println(user.toString());
    }
}
