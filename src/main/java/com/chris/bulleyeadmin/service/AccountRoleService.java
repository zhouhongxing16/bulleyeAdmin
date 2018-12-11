package com.chris.bulleyeadmin.service;


import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.mapper.AccountRoleMapper;
import com.chris.bulleyeadmin.pojo.AccountRole;
import com.chris.bulleyeadmin.service.BaseService;
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
