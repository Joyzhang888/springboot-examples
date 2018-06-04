package com.hong.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: IndexController
 * @Description: (测试使用Thymeleaf模板引擎渲染web视图)
 * @author hong
 * @date 2017/4/20
 * @version v1.1
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(ModelMap map){
        map.addAttribute("host","http://www.baidu.com");
        return  "index";
    }

}
