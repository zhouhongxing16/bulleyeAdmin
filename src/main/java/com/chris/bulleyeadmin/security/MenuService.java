package com.chris.bulleyeadmin.security;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.dto.MenuDto;
import com.chris.bulleyeadmin.mapper.MenuMapper;
import com.chris.bulleyeadmin.pojo.Menu;
import com.chris.bulleyeadmin.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:48
 */
@Service
public class MenuService extends BaseService<Menu> {

    @Autowired
    MenuMapper menuMapper;

    @Override
    public BaseMapper<Menu> getMapper() {
        return menuMapper;
    }


    public List<MenuDto> getMenusByAccountId(String accountId){
        Map<String,Object> map = new HashMap<>(2);
        map.put("accountId",accountId);
        List<MenuDto> menus = menuMapper.getMenusByAccountId(map);
        List<MenuDto> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for(MenuDto menu : menus){
            // 一级菜单没有pId
            if(menu.getpId()==null){
                menuList.add(menu);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for(MenuDto menu : menuList){
            menu.setChildren(getChild(menu.getId(),menus));
        }
        return menuList;
    }

    public List<Menu> getMenusByRoleId(String roleId){
        Map<String,Object> map = new HashMap<>(2);
        map.put("roleId",roleId);
        return menuMapper.getMenusByRoleId(map);
    }

    public List<MenuDto> getAllMenus(){
        List<MenuDto> menus = menuMapper.getAllMenus();
        List<MenuDto> menuList = new ArrayList<>();

        // 先找到所有的一级菜单
        for(MenuDto menu : menus){
            // 一级菜单没有pId
            if(menu.getpId()==null){
                menuList.add(menu);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for(MenuDto menu : menuList){
            menu.setChildren(getChild(menu.getId(),menus));
        }

        return menuList;
    }

    private List<MenuDto> getChild(String id,List<MenuDto> menuList){
        // 子菜单
        List<MenuDto> childList = new ArrayList<>();
        //遍历所有节点，将父级菜单ID与传过来的ID做比较
        for(MenuDto menu:menuList){
            if(menu.getrId()!=null){
                menu.setOpen(true);
                menu.setChecked(true);
            }
            if(menu.getpId()!=null){
                if (menu.getpId().equals(id)){
                    menu.setChildren(getChild(menu.getId(),menuList));
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
       /* for(MenuDto menu:menuList){
            // 没有url子菜单还有子菜单
            if(menu.getUri()==null){
                menu.setChildMenu(getChild(menu.getId(),menuList));
            }
        }*/
        if (childList.size()==0){
            return null;
        }
        return childList;
    }
}
