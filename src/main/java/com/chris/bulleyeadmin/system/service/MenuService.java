package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.dto.MenuDto;
import com.chris.bulleyeadmin.system.mapper.MenuMapper;
import com.chris.bulleyeadmin.system.pojo.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override
    public JsonResult update(Menu obj) {
        int updateCount = getMapper().updateByPrimaryKey(obj);
        String msg = updateCount>0?"成功更新"+updateCount+"条记录":"数据更新失败！";
        return new JsonResult(updateCount>0?true:false,null,msg,null, HttpStatus.OK);
    }

    public List<MenuDto> getMenusByAccountId(String accountId){
        Map<String,Object> map = new HashMap<>(2);
        map.put("accountId",accountId);
        List<MenuDto> menus = menuMapper.getMenusByAccountId(map);
        List<MenuDto> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for(MenuDto menu : menus){
            // 一级菜单没有pId
            if(menu.getParentId().isEmpty()){
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
            if(menu.getParentId()==null){
                menuList.add(menu);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for(MenuDto menu : menuList){
            menu.setChildren(getChild(menu.getId(),menus));
        }
        return menuList;
    }




    public List<MenuDto> getOrganizationMenus(Map<String,Object> map){
        List<MenuDto> menus = menuMapper.getOrganizationMenus(map);
        List<MenuDto> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for(MenuDto menu : menus){
            // 一级菜单没有pId
            if(menu.getParentId()==null){
                menuList.add(menu);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        List<MenuDto> list = null;
        for(MenuDto menu : menuList){
            list = getChild(menu.getId(),menus);
            menu.setIsLeaf(list==null?true:false);
            menu.setChildren(list);
        }
        return menuList;
    }

    public List<MenuDto> getOrganizationAuthMenus(Map<String,Object> map){
        List<MenuDto> menus = menuMapper.getOrganizationAuthMenus(map);
        List<MenuDto> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for(MenuDto menu : menus){
            // 一级菜单没有pId
            if(menu.getParentId()==null){
                menuList.add(menu);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        List<MenuDto> list = null;
        for(MenuDto menu : menuList){
            list = getChild(menu.getId(),menus);
            menu.setIsLeaf(list==null?true:false);
            menu.setChildren(list);
        }
        return menuList;
    }

    private List<MenuDto> getChild(String id,List<MenuDto> menuList){
        // 子菜单
        List<MenuDto> childList = new ArrayList<>();
        //遍历所有节点，将父级菜单ID与传过来的ID做比较
        for(MenuDto menu:menuList){
            if(menu.getParentId()!=null){
                if (menu.getParentId().equals(id)){
                    menu.setChildren(getChild(menu.getId(),menuList));
                    menu.setIsLeaf(getChild(menu.getId(),menuList)==null?true:false);
                    childList.add(menu);
                }
            }
        }
        if (childList.size()==0){
            return null;
        }
        return childList;
    }

}
