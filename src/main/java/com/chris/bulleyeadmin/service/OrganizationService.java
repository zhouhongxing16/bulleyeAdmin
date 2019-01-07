package com.chris.bulleyeadmin.service;

import com.chris.bulleyeadmin.config.BaseMapper;
import com.chris.bulleyeadmin.mapper.OrganizationMapper;
import com.chris.bulleyeadmin.pojo.Organization;
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
