package com.hong.service.impl;

import com.hong.dao.StudentDao;
import com.hong.domain.Student;
import com.hong.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by hong on 2017/5/8.
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentDao studentDao;


    /**
     * 保存学生信息
     *
     * @param student
     * @return
     */
    @Override
    public Student save(Student student) {
        return studentDao.save(student);
    }


    /**
     * 根据id查询学生信息
     *
     * @param id
     * @return
     */
    @Cacheable(value = "studentCache", key = "'student_'+#id")
    @Override
    public Student findById(Long id) {
        return studentDao.findOne(id);
    }

    /**
     * 更新学生信息
     *
     * @param student
     * @return
     */
    @CachePut(value = "studentCache", key = "'student_'+#student.getId()")
    @Override
    public Student update(Student student) {
        return studentDao.save(student);
    }


    /**
     * 根据id删除学生信息
     *
     * @param id
     */
    @CacheEvict(value = "studentCache", key = "'student_'+#id")
    @Override
    public void delete(Long id) {
        studentDao.delete(id);
    }
}
