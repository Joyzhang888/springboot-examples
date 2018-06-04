package com.hong;

import com.hong.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



/**
 * Created by hong on 2017/4/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
/** @SpringBootTest 在1.4 版本后替换@SpringApplicationConfiguration(Application.class) 加载当前spring boot 环境  **/
@SpringBootTest(classes ={Application.class})
public class ApplicationTest {

    private static final Log log = LogFactory.getLog(ApplicationTest.class);

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ConfigPrefixProperties configPrefixProperties;

    @Autowired
    private BindProperties bindProperties;

    @Autowired
    private ProfileProperties profileProperties;

    @Autowired
    private ArrayBindProperties arrayBindProperties;

    @Test
    public void test(){
        log.info("=========================configProperties========================="+configProperties.getName());
        log.info("随机数测试输出：");
        log.info("随机字符串 : " + configProperties.getValue());
        log.info("随机int : " + configProperties.getNumber());
        log.info("随机long : " + configProperties.getBignumber());
        log.info("随机10以下 : " + configProperties.getTest1());
        log.info("随机10-20 : " + configProperties.getTest2());
        log.info("在配置文件中没有定义key的情况下，blogProperties 中给定默认值:"+configProperties.getTt());
        log.info("properties 参数引用："+configProperties.getText());
    }


    @Test
    public void testProfix(){
        log.info("======================configPrefixProperties============================"+configPrefixProperties.getName());
        log.info("随机数测试输出：");
        log.info("随机字符串 : " + configPrefixProperties.getValue());
        log.info("随机int : " + configPrefixProperties.getNumber());
        log.info("随机long : " + configPrefixProperties.getBignumber());
        log.info("随机10以下 : " + configPrefixProperties.getTest1());
        log.info("随机10-20 : " + configPrefixProperties.getTest2());
    }


    /**
     *  松散的绑定 PORT->port  context-path->contextPath
     */
    @Test
    public void testBind(){
         log.info("port: "+bindProperties.getIpport());
         log.info("contextPath: "+bindProperties.getContextPath());
    }


    @Test
    public void testProfile(){
         log.info("当前环境的ip地址："+profileProperties.getAddress());
         log.info("当前环境的端口："+profileProperties.getPort());
    }


    @Test
    public void arrayBind(){
         arrayBindProperties.getServers().forEach(System.out::println);
    }
}
