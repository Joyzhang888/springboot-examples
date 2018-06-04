package com.hong.mapper;

import com.hong.common.util.MyMapper;
import com.hong.domain.Role;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {

    List<Role> getRoleByUserId(Integer id);
}