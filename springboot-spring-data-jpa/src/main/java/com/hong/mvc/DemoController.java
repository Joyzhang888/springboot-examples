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

    @RequestMapping("/add")
    @ResponseBody
    public Demo add() {
        return demoService.add(new Demo("Demo"));
    }

    @RequestMapping("/addList")
    @ResponseBody
    public List<Demo> addDemoList(){
        List<Demo> demoList= Stream.of(new Demo("张三"),new Demo("李四"),new Demo("王五")).collect(Collectors.toList());
        return demoService.addMemoList(demoList);
    }


    @RequestMapping("/update")
    public Demo update(Long id){
        Demo demo =new Demo();
        demo.setId(id);
        demo.setName("hong");
        return demoService.update(demo);
    }


    @RequestMapping("/findDemoAll")
    @ResponseBody
    public List<Demo> findDemoAll(){
        return  demoService.findDemoAll();
    }



    @RequestMapping("/del")
    public int del(Long id){
        return  demoService.del(id);
    }


}
