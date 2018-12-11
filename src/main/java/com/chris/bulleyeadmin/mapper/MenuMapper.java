package com.chris.bulleyeadmin.mapper;


import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.dto.MenuDto;
import com.chris.bulleyeadmin.pojo.Menu;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface MenuMapper extends BaseMapper<Menu> {

     List<MenuDto> getAllMenus();

     List<MenuDto> getMenusByAccountId(Map<String, Object> map);

     List<Menu> getMenusByRoleId(Map<String, Object> map);
}