package com.hong.service.impl;

import com.hong.domain.Role;
import com.hong.mapper.RoleMapper;
import com.hong.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hong on 2017/5/7.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleByUserId(Integer id) {
        return roleMapper.getRoleByUserId(id);
    }
}
