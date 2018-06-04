package com.hong.service;

import com.hong.dao.DemoDao;
import com.hong.domain.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hong on 2017/4/25.
 */
@Service
public class DemoService {


    @Autowired
    private DemoDao demoDao;

    /**
     * 根据id 获取demo 对象实例.
     * @param id
     * @return
     */
    public Demo getById(Long id){
        return  demoDao.getById(id);
    }

}
