package com.hong.service;

import com.hong.dao.DemoDao;
import com.hong.domain.Demo;
import org.hibernate.SessionFactory;
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
     * 添加
     *
     * @param demo
     */
    public Demo add(Demo demo) {
        return demoDao.save(demo);
    }

    /**
     * 添加一组demo
     * @param demoList
     * @return
     */
    public List<Demo> addMemoList(List<Demo> demoList) {
        return demoDao.save(demoList);
    }

    /**
     * 更新
     * @param demo
     * @return
     */
    public Demo update(Demo demo) {
        return demoDao.save(demo);
    }

    /**
     * 查找所有demo
     * @return
     */
    public List<Demo> findDemoAll() {
        return  demoDao.findAll();
    }

    /**
     * 根据id 删除一条记录
     * @param id
     * @return
     */
    public int del(Long id) {
        demoDao.delete(id);
        return 1;
    }
}
