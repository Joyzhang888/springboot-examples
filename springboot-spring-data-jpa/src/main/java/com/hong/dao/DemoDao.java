package com.hong.dao;

import com.hong.domain.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by hong on 2017/4/25.
 */
public interface DemoDao extends JpaRepository<Demo, Long> {


}
