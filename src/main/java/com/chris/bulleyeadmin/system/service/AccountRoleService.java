package com.chris.bulleyeadmin.system.service;


import com.chris.bulleyeadmin.common.mapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.AccountRoleMapper;
import com.chris.bulleyeadmin.system.pojo.AccountRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:36
 */
@Service
public class AccountRoleService extends BaseService<AccountRole> {

    @Autowired
    AccountRoleMapper accountRoleMapper;

    @Override
    public BaseMapper<AccountRole> getMapper() {
        return accountRoleMapper;
    }
}
