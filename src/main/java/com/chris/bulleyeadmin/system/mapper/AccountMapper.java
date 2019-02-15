package com.chris.bulleyeadmin.system.mapper;

import com.chris.bulleyeadmin.common.mapper.BaseMapper;
import com.chris.bulleyeadmin.system.dto.AccountDto;
import com.chris.bulleyeadmin.system.pojo.Account;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


public interface AccountMapper extends BaseMapper<Account> {

     AccountDto getAccountByUserName(String userName);
}