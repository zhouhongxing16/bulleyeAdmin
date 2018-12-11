package com.chris.bulleyeadmin.pojo;


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

    public User(String id, String username, String password, String orgId, String staffId, String departmentId, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.orgId = orgId;
        this.staffId = staffId;
        this.departmentId = departmentId;
    }

    public User(){
        super("admin", "admin", new ArrayList<>());
    }


    private String id;

    private String orgId;

    private String staffId;

    private String departmentId;

    private List<Role> role;

    private String staffName;

    private String roleName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
