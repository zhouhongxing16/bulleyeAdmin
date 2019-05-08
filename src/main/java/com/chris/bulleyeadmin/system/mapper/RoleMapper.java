package com.chris.bulleyeadmin.system.mapper;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.system.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRolesByAccountId(@Param("accountId") String accountId);

}