package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.RoleMenuMapper;
import com.chris.bulleyeadmin.system.pojo.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:38
 */
@Service
public class RoleMenuService extends BaseService<RoleMenu> {

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public BaseMapper<RoleMenu> getMapper() {
        return roleMenuMapper;
    }


    public JsonResult createRoleMenu(List<RoleMenu> list){
        JsonResult result = new JsonResult();
        if(list.size()==0){
            result.setSuccess(false);
            result.setMessage("错误！数据不能为空！");
        }else{
            Map<String,Object> params = new HashMap<>(2);
            params.put("roleId",list.get(0).getRoleId());
            roleMenuMapper.deleteByParams(params);
            for(RoleMenu roleMenu :list){
                roleMenuMapper.insert(roleMenu);
            }
            result.setSuccess(true);
            result.setMessage("菜单授权成功！");

        }
        return result;
    }
}
