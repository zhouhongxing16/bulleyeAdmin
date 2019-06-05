package com.chris.bulleyeadmin.system.service;


import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.mapper.AccountRoleMapper;
import com.chris.bulleyeadmin.system.pojo.AccountRole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:36
 */
@Service
public class AccountRoleService extends BaseService<AccountRole> {

    @Autowired
    AccountRoleMapper accountRoleMapper;

    @Override
    public BaseMapper<AccountRole> getMapper() {
        return accountRoleMapper;
    }

    public JsonResult saveAccountRoles(Map<String, String> map) {
        JsonResult result = new JsonResult();
        String accountId = map.get("accountId");
        if (StringUtils.isEmpty(accountId)) {
            result.setSuccess(false);
            result.setMessage("accountId不能为空！");
        } else {
            String ids = map.get("roleIds");
            int count = accountRoleMapper.deleteByAccountId(accountId);
            if (StringUtils.isEmpty(ids)) {
                result.setMessage("角色清除成功！");
            } else {
                String roleIds[] = ids.split(",");
                AccountRole accountRole = null;
                for (String id : roleIds) {
                    accountRole = new AccountRole();
                    accountRole.setAccountId(accountId);
                    accountRole.setRoleId(id);
                    accountRoleMapper.insert(accountRole);
                }
                result.setMessage("角色授权成功！");
            }

            result.setSuccess(true);
        }
        return result;
    }
}
