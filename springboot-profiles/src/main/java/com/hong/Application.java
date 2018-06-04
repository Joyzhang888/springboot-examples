package com.hong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hong on 2017/4/19.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 注意：如果改变了spring boot 配置文件的位置，在启动的时候指定位置
        //       测试时，@RunWith(SpringRunner.class)
        //               @SpringBootTest(classes= PlutoIsOmsWebApplication.class)
        //               @TestPropertySource(locations="classpath:conf/env/application.properties")
        //        也需要指定下；
        // System.setProperty("spring.config.location","classpath:conf/env/application.properties");
        SpringApplication.run(Application.class,args);
    }
}
