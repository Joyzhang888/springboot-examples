package com.hong.service;

import com.hong.domain.User;

/**
 * Created by hong on 2017/5/6.
 */
public interface UserService {

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    User findUserByName(String name);

    int save(User user);
}
