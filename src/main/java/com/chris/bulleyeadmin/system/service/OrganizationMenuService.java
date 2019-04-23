package com.chris.bulleyeadmin.system.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.Help;
import com.chris.bulleyeadmin.system.mapper.OrganizationMenuMapper;
import com.chris.bulleyeadmin.system.pojo.OrganizationMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 15:39
 * @Description:
 */
@Service
public class OrganizationMenuService extends BaseService<OrganizationMenu> {

    @Autowired
    OrganizationMenuMapper organizationMenuMapper;

    @Override
    public BaseMapper<OrganizationMenu> getMapper() {
        return organizationMenuMapper;
    }

    public JsonResult createOrganizationMenu(List<OrganizationMenu> list){
        JsonResult result = new JsonResult();
        if(list.size()==0){
            result.setSuccess(false);
            result.setMessage("错误！数据不能为空！");
        }else{
            Map<String,Object> params = new HashMap<>(2);
            params.put("organizationId",list.get(0).getOrganizationId());
            organizationMenuMapper.deleteByParams(params);
            for(OrganizationMenu organizationMenu :list){
                organizationMenuMapper.insert(organizationMenu);
            }
            result.setSuccess(true);
            result.setMessage("菜单授权成功！");

        }
        return result;
    }
}
