package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chris.bulleyeadmin.system.pojo.RoleMenuAuth;
import  com.chris.bulleyeadmin.system.mapper.RoleMenuAuthMapper;

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
}