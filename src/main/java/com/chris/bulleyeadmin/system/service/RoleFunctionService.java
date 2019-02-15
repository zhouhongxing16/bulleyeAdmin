package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.RoleFunctionMapper;
import com.chris.bulleyeadmin.system.pojo.RoleFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:37
 * @Description:
 */
@Service
public class RoleFunctionService extends BaseService<RoleFunction> {

    @Autowired
    RoleFunctionMapper roleFunctionMapper;

    @Override
    public BaseMapper<RoleFunction> getMapper() {
        return roleFunctionMapper;
    }
}
