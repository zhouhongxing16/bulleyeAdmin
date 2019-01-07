package com.chris.bulleyeadmin.service;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.mapper.MenuFunctionMapper;
import com.chris.bulleyeadmin.pojo.MenuFunction;
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
