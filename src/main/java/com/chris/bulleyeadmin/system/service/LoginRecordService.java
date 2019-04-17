package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.LoginRecordMapper;
import com.chris.bulleyeadmin.system.pojo.LoginRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chris.bulleyeadmin.system.service.LoginRecordService;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-04-17 18:00
 */
@Service
public class LoginRecordService extends BaseService<LoginRecord> {

    @Autowired
    private LoginRecordMapper loginRecordMapper;

    @Override
    public BaseMapper<LoginRecord> getMapper() {
        return loginRecordMapper;
    }
}