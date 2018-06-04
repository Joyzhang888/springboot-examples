package com.hong.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hong on 2017/4/23.
 */
@Controller
public class HelloVelocityController {



    @RequestMapping("/hello")
    public String index(Model model){
        model.addAttribute("name","hong");
        return "index";
    }
}
