package com.chris.bulleyeadmin.service;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.mapper.StaffMapper;
import com.chris.bulleyeadmin.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-14 23:19
 */
@Service
public class StaffService extends BaseService<Staff> {

    @Autowired
    StaffMapper staffMapper;

    @Override
    public BaseMapper<Staff> getMapper() {
        return staffMapper;
    }

}
