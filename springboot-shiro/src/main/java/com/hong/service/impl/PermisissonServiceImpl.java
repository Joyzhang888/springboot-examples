package com.hong.service.impl;

import com.hong.domain.Permission;
import com.hong.mapper.PermissionMapper;
import com.hong.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hong on 2017/5/7.
 */
@Service
public class PermisissonServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionByUserId(Integer id) {
        return permissionMapper.getPermissionByUserId(id);
    }
}
