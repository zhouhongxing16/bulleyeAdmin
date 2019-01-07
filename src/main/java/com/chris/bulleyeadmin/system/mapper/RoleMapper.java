package com.chris.bulleyeadmin.system.mapper;

import com.chris.bulleyeadmin.common.mapper.BaseMapper;
import com.chris.bulleyeadmin.system.pojo.Role;

import java.util.List;
import java.util.Map;


public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRolesByAccountId(Map<String, Object> map);

}