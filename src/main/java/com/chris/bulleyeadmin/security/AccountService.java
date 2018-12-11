package com.chris.bulleyeadmin.security;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.mapper.AccountMapper;
import com.chris.bulleyeadmin.pojo.Account;
import com.chris.bulleyeadmin.service.BaseService;
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

    public Account getAccountByUserName(String userName){
        return accountMapper.getAccountByUserName(userName);
    }
}
