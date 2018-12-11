package com.chris.bulleyeadmin.mapper;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.pojo.Role;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRolesByAccountId(Map<String, Object> map);

}