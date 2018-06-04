package com.hong.mapper.master;

import com.hong.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hong on 2017/5/2.
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> findAll();
}
