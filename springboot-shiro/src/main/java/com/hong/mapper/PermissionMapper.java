package com.hong.mapper;

import com.hong.common.util.MyMapper;
import com.hong.domain.Permission;

import java.util.List;

public interface PermissionMapper extends MyMapper<Permission> {

    List<Permission> getPermissionByUserId(Integer id);
}