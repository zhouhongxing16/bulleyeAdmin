package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.MenuFunctionMapper;
import com.chris.bulleyeadmin.system.pojo.MenuFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:38
 * @Description:
 */
@Service
public class MenuFunctionService extends BaseService<MenuFunction> {

    @Autowired
    MenuFunctionMapper menuFunctionMapper;

    @Override
    public BaseMapper<MenuFunction> getMapper() {
        return menuFunctionMapper;
    }
}
