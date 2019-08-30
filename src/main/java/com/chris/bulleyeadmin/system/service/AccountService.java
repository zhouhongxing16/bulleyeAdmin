package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.dto.AccountDto;
import com.chris.bulleyeadmin.system.mapper.AccountMapper;
import com.chris.bulleyeadmin.system.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-11 11:52
 */
@Service
public class AccountService extends BaseService<Account> {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BaseMapper<Account> getMapper() {
        return accountMapper;
    }

    public AccountDto getAccountByUserName(String userName){
        return accountMapper.getAccountByUserName(userName);
    }

    public AccountDto getAccountByStaffMobile(String userName){
        return accountMapper.getAccountByStaffMobile(userName);
    }
}
