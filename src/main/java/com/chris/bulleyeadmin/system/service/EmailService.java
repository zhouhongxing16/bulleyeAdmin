package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.EmailMapper;
import com.chris.bulleyeadmin.system.pojo.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-20 14:09
 */
@Service
public class EmailService extends BaseService<Email> {

    @Autowired
    EmailMapper emailMapper;

    @Override
    public BaseMapper<Email> getMapper() {
        return emailMapper;
    }
}
