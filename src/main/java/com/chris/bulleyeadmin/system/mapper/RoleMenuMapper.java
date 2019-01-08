package com.chris.bulleyeadmin.system.mapper;

import com.chris.bulleyeadmin.common.mapper.BaseMapper;
import com.chris.bulleyeadmin.system.dto.RoleMenuDto;
import com.chris.bulleyeadmin.system.pojo.RoleMenu;

import java.util.List;
import java.util.Map;


public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<RoleMenuDto> getMenusByRoleId(Map<String, Object> map);


}