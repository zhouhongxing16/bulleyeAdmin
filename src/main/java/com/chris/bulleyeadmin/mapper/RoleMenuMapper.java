package com.chris.bulleyeadmin.mapper;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.dto.RoleMenuDto;
import com.chris.bulleyeadmin.pojo.RoleMenu;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<RoleMenuDto> getMenusByRoleId(Map<String, Object> map);

    List<Map<String,String>> getRoleAuthPaths();
}