package com.hong.web;

import com.hong.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hong on 2017/4/24.
 */
@Controller
public class HelloController {


    @RequestMapping("/helloEx")
    public String hello() throws Exception {
        throw   new Exception("发生异常");
    }


    @RequestMapping("myhello")
    public String myHello() throws MyException {
        throw  new MyException("发生异常");
    }

    @RequestMapping("/json")
    @ResponseBody
    public String json(){
        return "json";
    }


    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
