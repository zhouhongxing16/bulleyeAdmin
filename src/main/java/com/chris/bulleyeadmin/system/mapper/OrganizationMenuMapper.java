package com.chris.bulleyeadmin.system.mapper;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.system.pojo.Organization;
import com.chris.bulleyeadmin.system.pojo.OrganizationMenu;

import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 15:39
 * @Description:
 */
public interface OrganizationMenuMapper extends BaseMapper<OrganizationMenu> {

    int deleteByParams(Map<String,Object> params);
}
