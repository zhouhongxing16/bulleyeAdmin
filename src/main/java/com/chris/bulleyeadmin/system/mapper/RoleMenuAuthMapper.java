package com.chris.bulleyeadmin.system.mapper;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.system.pojo.RoleMenuAuth;

import java.util.List;
import java.util.Map;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date: 2019-05-16 21:29
 */
public interface RoleMenuAuthMapper extends BaseMapper<RoleMenuAuth> {
    List<Map<String, String>> getRoleAuthPaths();
    List<Map<String, String>> getAuthByMenuAndRoleId(Map<String,String> param);
}