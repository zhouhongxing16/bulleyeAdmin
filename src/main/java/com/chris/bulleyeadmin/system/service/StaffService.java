package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.StaffMapper;
import com.chris.bulleyeadmin.system.pojo.Staff;
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
