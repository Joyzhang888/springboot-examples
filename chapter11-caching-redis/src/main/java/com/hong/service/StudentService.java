package com.hong.service;

import com.hong.domain.Student;

/**
 * Created by hong on 2017/5/8.
 */
public interface StudentService {

    Student save(Student student);

    Student findById(Long id);

    Student update(Student student);

    void delete(Long id);


}
