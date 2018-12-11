package com.chris.bulleyeadmin.service;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.mapper.RoleMenuMapper;
import com.chris.bulleyeadmin.pojo.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
