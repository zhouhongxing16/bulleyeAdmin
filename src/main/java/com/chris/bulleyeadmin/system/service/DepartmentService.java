package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.DepartmentMapper;
import com.chris.bulleyeadmin.system.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 15:57
 * @Description:
 */
@Service
public class DepartmentService extends BaseService<Department> {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public BaseMapper<Department> getMapper() {
        return departmentMapper;
    }
}
