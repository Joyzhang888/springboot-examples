package com.hong;

import com.hong.domain.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by hong on 2017/4/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication(scanBasePackageClasses={Application.class})
public class MutilDatasourceTest {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;


    @Test
    public void test(){
        String sql = "select * from demo ";
        RowMapper<Demo> rowMapper = new BeanPropertyRowMapper<>(Demo.class);
        List<Demo> demos= primaryJdbcTemplate.query(sql,rowMapper);
        if(!CollectionUtils.isEmpty(demos)) {
            demos.forEach(e -> System.out.println(e.toString()));
        }
        List<Demo> demos2 =secondaryJdbcTemplate.query(sql,rowMapper);
        if(!CollectionUtils.isEmpty(demos2)) {
            demos2.forEach(demo2 -> System.out.println(demo2.toString()));
        }
    }

}
