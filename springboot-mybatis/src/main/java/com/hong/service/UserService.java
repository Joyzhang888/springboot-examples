package com.hong.service;

import com.hong.mapper.UserMapper;
import com.hong.xmlmapper.XUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hong.domain.User;

/**
 * Created by hong on 2017/4/25.
 */
@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private XUserMapper xUserMapper;

    /**
     * 根据id 获取用户信息
     * @param id
     * @return
     */
    public User getUserById(Long id){
        return  userMapper.findById(id);
    }

    public User getXUserById(Long id) {return  xUserMapper.findById(id);};

    /**
     * 添加用户
     * @param user
     * @return
     */
    public int add(User user) {
        return userMapper.insert(user.getName(),user.getAge());
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public int update(User user) {
        return userMapper.update(user);
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    public int delete(Long id) {
        return userMapper.delete(id);
    }
}
