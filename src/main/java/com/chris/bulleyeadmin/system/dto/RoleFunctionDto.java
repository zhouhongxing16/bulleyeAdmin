package com.chris.bulleyeadmin.system.dto;

import com.chris.bulleyeadmin.system.pojo.RoleFunction;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:16
 * @Description:
 */
public class RoleFunctionDto extends RoleFunction {
    private String roleName;

    private String roleCode;

    private String functionCode;

    private String menuPath;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }
}
