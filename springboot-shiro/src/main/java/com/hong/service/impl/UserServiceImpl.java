package com.hong.service.impl;

import com.hong.domain.User;
import com.hong.mapper.UserMapper;
import com.hong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hong on 2017/5/6.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String name) {
        User user =new User();
        user.setUsername(name);
        return userMapper.selectOne(user);
    }

    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }


}
