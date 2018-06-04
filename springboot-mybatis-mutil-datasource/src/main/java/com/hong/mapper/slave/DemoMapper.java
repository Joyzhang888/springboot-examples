package com.hong.mapper.slave;

import com.hong.domain.Demo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hong on 2017/5/2.
 */
@Mapper
public interface DemoMapper {

    @Select("select * from demo")
    List<Demo> findAll();
}
