package com.hong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hong on 2017/4/24.
 */
@Controller
public class HelloController {


    @RequestMapping("/hello")
    public String index(){
        return "index";
    }

}
