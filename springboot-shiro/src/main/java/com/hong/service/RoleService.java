package com.hong.service;

import com.hong.domain.Role;

import java.util.List;

/**
 * Created by hong on 2017/5/7.
 */
public interface RoleService {

    List<Role> getRoleByUserId(Integer id);
}
