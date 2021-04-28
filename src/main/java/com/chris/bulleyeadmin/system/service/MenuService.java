package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.system.dto.MenuDto;
import com.chris.bulleyeadmin.system.mapper.MenuAuthMapper;
import com.chris.bulleyeadmin.system.mapper.MenuMapper;
import com.chris.bulleyeadmin.system.pojo.Menu;
import com.chris.bulleyeadmin.system.pojo.MenuAuth;
import com.chris.bulleyeadmin.system.pojo.User;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    MenuAuthMapper menuAuthMapper;

    @Override
    public BaseMapper<Menu> getMapper() {
        return menuMapper;
    }


    @Override
    public Integer add(Menu obj) {
        int insertCount = getMapper().insert(obj);

        //增加菜单时默认增加增删改查功能权限
        if (insertCount > 0 && !StringUtils.isEmpty(obj.getPath())) {

            MenuAuth maList = new MenuAuth();
            maList.setMenuId(obj.getId());
            maList.setCode("list");
            maList.setName("增加");
            maList.setPath("/" + obj.getCode() + "/list");
            maList.setStatus(1);
            menuAuthMapper.insert(maList);

            MenuAuth maAdd = new MenuAuth();
            maAdd.setMenuId(obj.getId());
            maAdd.setCode("create");
            maAdd.setName("增加");
            maAdd.setPath("/" + obj.getCode() + "/create");
            maAdd.setStatus(1);
            menuAuthMapper.insert(maAdd);


            MenuAuth maEdit = new MenuAuth();
            maEdit.setMenuId(obj.getId());
            maEdit.setCode("update");
            maEdit.setName("编辑");
            maEdit.setPath("/" + obj.getCode() + "/update");
            maEdit.setStatus(1);
            menuAuthMapper.insert(maEdit);

            MenuAuth maDelete = new MenuAuth();
            maDelete.setMenuId(obj.getId());
            maDelete.setCode("delete");
            maDelete.setName("删除");
            maDelete.setPath("/" + obj.getCode() + "/delete");
            maDelete.setStatus(1);
            menuAuthMapper.insert(maDelete);
        }
        return insertCount;

    }

    @Override
    public JsonResult update(Menu obj) {
        int updateCount = getMapper().updateByPrimaryKey(obj);
        MenuAuth auth = new MenuAuth();
        auth.setMenuId(obj.getId());
        List<MenuAuth> menuAuthList = menuAuthMapper.select(auth);
        if(menuAuthList.size()==0){

            MenuAuth maList = new MenuAuth();
            maList.setMenuId(obj.getId());
            maList.setCode("list");
            maList.setName("列表查看");
            maList.setPath("/" + obj.getCode() + "/list");
            maList.setStatus(1);
            menuAuthMapper.insert(maList);

            MenuAuth maAdd = new MenuAuth();
            maAdd.setMenuId(obj.getId());
            maAdd.setCode("create");
            maAdd.setName("增加");
            maAdd.setPath("/" + obj.getCode() + "/create");
            maAdd.setStatus(1);
            menuAuthMapper.insert(maAdd);


            MenuAuth maEdit = new MenuAuth();
            maEdit.setMenuId(obj.getId());
            maEdit.setCode("update");
            maEdit.setName("编辑");
            maEdit.setPath("/" + obj.getCode() + "/update");
            maEdit.setStatus(1);
            menuAuthMapper.insert(maEdit);

            MenuAuth maDelete = new MenuAuth();
            maDelete.setMenuId(obj.getId());
            maDelete.setCode("delete");
            maDelete.setName("删除");
            maDelete.setPath("/" + obj.getCode() + "/delete");
            maDelete.setStatus(1);
            menuAuthMapper.insert(maDelete);
        }
        String msg = updateCount>0?"成功更新"+updateCount+"条记录":"数据更新失败！";
        return new JsonResult(updateCount>0?true:false,null,msg,null, HttpStatus.OK.value());
    }

    public List<MenuDto> getMenusByAccountId(){
        User user = AuthUtil.getCurrentUser();
        Map<String, Object> map = new HashMap<>(2);
        map.put("accountId", user.getId());
        map.put("organizationId", user.getOrganizationId());
        List<MenuDto> menus = menuMapper.getMenusByAccountId(map);
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
        return menuList;
    }

    public List<MenuDto> getMenusByRoleId(String roleId){
        Map<String,String> map = new HashMap<>(2);
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
            if(menu!=null && menu.getParentId()==null){
                menuList.add(menu);
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        List<MenuDto> list = null;
        for(MenuDto menu : menuList){
            list = getChild(menu.getId(),menus);
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
            if(menu!=null && menu.getParentId()==null){
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
