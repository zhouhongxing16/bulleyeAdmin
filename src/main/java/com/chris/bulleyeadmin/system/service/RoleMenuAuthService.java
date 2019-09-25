package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chris.bulleyeadmin.system.pojo.RoleMenuAuth;
import  com.chris.bulleyeadmin.system.mapper.RoleMenuAuthMapper;

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
}