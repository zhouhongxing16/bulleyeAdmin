package com.chris.bulleyeadmin.system.dto;

import com.chris.bulleyeadmin.system.pojo.Account;
import com.chris.bulleyeadmin.system.pojo.Organization;
import com.chris.bulleyeadmin.system.pojo.Staff;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-14 22:59
 */
public class AccountDto extends Account {
    private static final long serialVersionUID = -8784038827964503299L;
    private String organizationName;

    public AccountDto() {
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
