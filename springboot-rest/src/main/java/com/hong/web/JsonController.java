package com.hong.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hong.domain.User;

import java.util.Date;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @ClassName: JsonController
 * @Description: (Spring boot 返回json数据)
 * @author hong
 * @date 2017/4/20
 * @version v1.1
 */
@RestController
/**
 * 如果我们不使用默认的json 解析工具，怎么替换成比较习惯的json框架是fastjson呢？ 有两种方式：
 * 第一种方法就是：
 *（1）启动类继承extends WebMvcConfigurerAdapter
 *（2）覆盖方法configureMessageConverters
 *
 * 第二种方方法是：
 *（1）在App.java启动类中，注入Bean : HttpMessageConverters
 *
 */
public class JsonController {


    @Bean
    public User newUser(){
        User user =new User();
        user.setId(1);
        user.setAge(11);
        user.setName("张三");
        user.setMsg(".....");
        user.setCreateTime(new Date());
        return user;
    }



    @Autowired
    @Qualifier("newUser")
    private User user;


    /**
     * Spring Boot也是引用了JSON解析包Jackson，那么自然我们就可以在Demo对象上使用Jackson提供的json属性的注解，
     * 对时间进行格式化，对一些字段进行忽略等等
     * 注： 查看pom 依赖时可以看到spring boot 默认添加了jackson 的包。
     * @return
     */
    @RequestMapping(value="/user",produces = MediaType.APPLICATION_JSON_VALUE)//,produces = "application/json; charset=utf-8"  在设置第三方json 解析框架fastjson 时，统一处理
    public User getUser(){

        //添加hateoas对rest服务动态的链接信息的支持.
        //当我们访问资源/user 时，会把其他注册的操作以links href 的方式公布出去.
        user.add(linkTo(methodOn(JsonController.class).setUserName(user.getName())).withSelfRel());
        user.add(linkTo(methodOn(JsonController.class).setUserAge(user.getAge())).withSelfRel());

        return user;
    }


    @RequestMapping(value = "/user/set/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public User setUserName(@RequestParam("name") String name){
        user.setName(name);
        return user;
    }

    @RequestMapping(value = "/user/set/age",produces = MediaType.APPLICATION_JSON_VALUE)
    public User setUserAge(@RequestParam("age") int age){
        user.setAge(age);
        return user;
    }






}
