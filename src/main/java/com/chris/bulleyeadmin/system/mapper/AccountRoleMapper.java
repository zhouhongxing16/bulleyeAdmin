package com.chris.bulleyeadmin.system.mapper;


import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.system.pojo.AccountRole;
import org.apache.ibatis.annotations.Param;


public interface AccountRoleMapper extends BaseMapper<AccountRole> {

    int deleteByAccountId(@Param("accountId") String accountId);
}