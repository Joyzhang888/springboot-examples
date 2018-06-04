package com.hong.service;

import com.hong.domain.Permission;

import java.util.List;

/**
 * Created by hong on 2017/5/7.
 */
public interface PermissionService {

    List<Permission> getPermissionByUserId(Integer id);
}
