package com.hong.mvc;

import com.hong.domain.Demo;
import com.hong.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hong on 2017/4/25.
 */
@Controller
public class DemoController {

    @Autowired
    private DemoService demoService;


    /**
     * 根据id 获取demo 对象
     * @param id
     * @return
     */
    @RequestMapping("/getById")
    @ResponseBody
    public Demo getById(Long id){
        return  demoService.getById(id);
    }

}
