package com.hong.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by hong on 2017/4/23.
 */
@Controller
public class HelloFreemarkerController {


    @RequestMapping("/hello")
    public String index(Map<String,Object> map){
        map.put("name","hong");
        return "index";
    }
}
