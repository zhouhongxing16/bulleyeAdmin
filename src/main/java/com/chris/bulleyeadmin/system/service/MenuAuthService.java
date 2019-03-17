package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.MenuAuthMapper;
import com.chris.bulleyeadmin.system.pojo.MenuAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-03-17 21:09
 */
@Service
public class MenuAuthService extends BaseService<MenuAuth> {

    @Autowired
    private MenuAuthMapper menuAuthMapper;

    @Override
    public BaseMapper<MenuAuth> getMapper() {
        return menuAuthMapper;
    }
}