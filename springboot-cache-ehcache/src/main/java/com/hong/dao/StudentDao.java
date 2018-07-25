package com.hong.dao;

import com.hong.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hong on 2017/5/8.
 */
public interface StudentDao extends JpaRepository<Student,Long> {
}
