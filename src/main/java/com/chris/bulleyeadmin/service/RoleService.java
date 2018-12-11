package com.chris.bulleyeadmin.service;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.mapper.RoleMapper;
import com.chris.bulleyeadmin.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:37
 */
@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public BaseMapper<Role> getMapper() {
        return roleMapper;
    }

    public List<Role> getRolesByAccountId(String accountId) {
        Map<String,Object> map = new HashMap<>(2);
        map.put("accountId",accountId);
        return roleMapper.getRolesByAccountId(map);
    }
}
