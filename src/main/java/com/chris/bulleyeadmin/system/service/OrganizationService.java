package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.mapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.OrganizationMapper;
import com.chris.bulleyeadmin.system.pojo.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 15:39
 * @Description:
 */
@Service
public class OrganizationService extends BaseService<Organization> {

    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public BaseMapper<Organization> getMapper() {
        return organizationMapper;
    }
}
