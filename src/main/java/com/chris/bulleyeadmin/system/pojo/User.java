package com.chris.bulleyeadmin.system.pojo;


import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-30 14:55
 */
public class User extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    public User(String id, Staff staff,String username, String password, String organizationId, String staffId, String departmentId, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.organizationId = organizationId;
        this.staffId = staffId;
        this.departmentId = departmentId;
        this.staff = staff;
    }

    public User(){
        super("admin", "admin", new ArrayList<>());
    }


    private String id;

    private String organizationId;

    private String staffId;

    private String departmentId;

    private List<Role> role;

    private Staff staff;

    private String staffName;

    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
