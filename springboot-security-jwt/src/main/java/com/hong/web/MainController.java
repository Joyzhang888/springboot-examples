package com.hong.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hong on 2017/6/16.
 */
@Controller
public class MainController {


    /**
     * 入口页面.
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
