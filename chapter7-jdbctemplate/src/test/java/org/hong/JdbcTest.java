package org.hong;

import com.hong.Application;
import com.hong.dao.DemoDao;
import com.hong.domain.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by hong on 2017/7/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@SpringBootConfiguration
public class JdbcTest {

    @Autowired
    private DemoDao demoDao;

    @Test
    public void testJdbc(){
        Demo demo = demoDao.getDemoById(1L);
        System.out.println(demo.toString());
    }

    @Test
    public void testMetaData(){
        List<Map<String, Object>> demoList = demoDao.getDemoList();
        for (Map<String, Object> stringObjectMap : demoList) {
            System.out.println(stringObjectMap);
        }
    }

    @Test
    public void TestInsert(){
        demoDao.save();
    }

    @Test
    public void testSupportedTransaction(){
        Boolean supported = demoDao.supportedTransaction();
        System.out.println("当前数据库是否支持事务： "+supported);
    }
}
