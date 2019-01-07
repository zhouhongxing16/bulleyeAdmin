package com.chris.bulleyeadmin.system.mapper;


import com.chris.bulleyeadmin.common.mapper.BaseMapper;
import com.chris.bulleyeadmin.system.dto.MenuDto;
import com.chris.bulleyeadmin.system.pojo.Menu;

import java.util.List;
import java.util.Map;


public interface MenuMapper extends BaseMapper<Menu> {

     List<MenuDto> getAllMenus();

     List<MenuDto> getMenusByAccountId(Map<String, Object> map);

     List<Menu> getMenusByRoleId(Map<String, Object> map);
}