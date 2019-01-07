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
    private Staff staff;
    private String StaffName;
    private Organization organization;
    private String organizationName;

    public AccountDto() {
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String staffName) {
        StaffName = staffName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
