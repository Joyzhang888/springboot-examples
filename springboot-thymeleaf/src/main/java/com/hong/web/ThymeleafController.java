package com.hong.web;

import com.hong.domain.Book;
import com.hong.localMessage.LocaleMessageSourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hong.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author hong
 * @version v1.1
 * @ClassName: ThymeleafController
 * @Description: (Thymeleaf 模板语言特性练习)
 * @date 2017/4/21
 */

@Controller
public class ThymeleafController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private LocaleMessageSourceUtil localeMessageSourceUtil;




    /**
     * 标准表达式语法,它们分为四类：
     * 1.变量表达式
     * 2.选择或星号表达式
     * 3.文字国际化表达式
     * 4.URL表达式
     *
     * @return
     */
    @RequestMapping("/test")
    public String testThymeleaf(HttpServletRequest request, Model model) {
        model.addAttribute("hello", "hello thymeleaf");

        //1.变量表达式
        HttpSession session = request.getSession();
        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(18);
        user.setAddress("广东深圳");
        session.setAttribute("user", user);

        List<Book> books = new ArrayList<>
                (Arrays.asList(new Book(1, "java编程思想",87.5),
                new Book(2, "spring boot实战",79.0),
                new Book(3,"maven 实战",39.5)));
        model.addAttribute("books",books);

        return "thymeleaf_view";
    }

}
