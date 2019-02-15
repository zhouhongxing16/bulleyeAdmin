package com.chris.bulleyeadmin.system.mapper;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.system.pojo.RoleFunction;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:33
 * @Description:
 */
public interface RoleFunctionMapper extends BaseMapper<RoleFunction> {
    List<Map<String,String>> getRoleAuthPaths();
}
