package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.system.controller.RoleController;
import com.chris.bulleyeadmin.system.dto.MenuDto;
import com.chris.bulleyeadmin.system.mapper.MenuAuthMapper;
import com.chris.bulleyeadmin.system.mapper.MenuMapper;
import com.chris.bulleyeadmin.system.mapper.RoleMapper;
import com.chris.bulleyeadmin.system.pojo.Menu;
import com.chris.bulleyeadmin.system.pojo.MenuAuth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chris.bulleyeadmin.system.pojo.RoleMenuAuth;
import  com.chris.bulleyeadmin.system.mapper.RoleMenuAuthMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-05-16 21:29
 */
@Service
public class RoleMenuAuthService extends BaseService<RoleMenuAuth> {

    @Autowired
    private RoleMenuAuthMapper roleMenuAuthMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuAuthMapper menuAuthMapper;

    @Override
    public BaseMapper<RoleMenuAuth> getMapper() {
        return roleMenuAuthMapper;
    }

    public JsonResult getAuthByMenuAndRoleId(String menuId){
        Map<String,String> param = new HashMap<>();
        JsonResult result = new JsonResult();
        param.put("roleId", AuthUtil.getCurrentUser().getCurrentRole().getId());
        param.put("menuId",menuId);
        List<Map<String, String>> mapList =  roleMenuAuthMapper.getAuthByMenuAndRoleId(param);
        result.setData(mapList);
        result.setSuccess(true);
        result.setMessage("获取数据成功");
        return result;
    }

    public JsonResult getMenuAndAuthByRoleId(String roleId){
        Map<String,String> param = new HashMap<>();
        JsonResult result = new JsonResult();
        param.put("roleId", roleId);
        List<MenuDto> menus =  menuMapper.getMenusByRoleId(param);

        List<MenuDto> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for(MenuDto menu : menus){
            // 一级菜单没有pId
            if(StringUtils.isEmpty(menu.getParentId())){
                menuList.add(menu);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for(MenuDto menu : menuList){
            menu.setChildren(getChild(menu.getId(),menus));
        }

        result.setData(menuList);
        result.setSuccess(true);
        result.setMessage("获取数据成功");
        return result;
    }

    private List<MenuDto> getChild(String id,List<MenuDto> menuList){
        // 子菜单
        List<MenuDto> childList = new ArrayList<>();
        //遍历所有节点，将父级菜单ID与传过来的ID做比较
        for(MenuDto menu:menuList){
            if(menu.getParentId()!=null){
                if (menu.getParentId().equals(id)){
                    menu.setChildren(getChild(menu.getId(),menuList));
                    if(getChild(menu.getId(),menuList)==null?true:false){
                        Map<String,Object> queryMap = new HashMap<>();
                        queryMap.put("menuId",menu.getId());
                        List<MenuAuth> menuAuths = menuAuthMapper.getListByParams(queryMap);
                        if(menuAuths.size()==0){
                            menu.setIsLeaf(true);
                        }else{
                            menu.setIsLeaf(false);
                            menu.setAuthList(menuAuths);
                        }
                    }else{
                        menu.setIsLeaf(true);
                    }
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