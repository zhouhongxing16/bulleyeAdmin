package com.chris.bulleyeadmin.system.mapper;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.system.dto.AccountDto;
import com.chris.bulleyeadmin.system.pojo.Account;


public interface AccountMapper extends BaseMapper<Account> {

     AccountDto getAccountByUserName(String userName);

     AccountDto getAccountByStaffMobile(String mobile);

     AccountDto getById(String id);
}