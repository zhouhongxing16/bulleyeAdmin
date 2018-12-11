package com.chris.bulleyeadmin.mapper;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.dto.AccountDto;
import com.chris.bulleyeadmin.pojo.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;


public interface AccountMapper extends BaseMapper<Account> {

     AccountDto getAccountByNameAndOrgId(Map<String,Object> map);

     Account getAccountByUserName(@Param("userName") String userName);
}