package com.chris.bulleyeadmin.dto;


import com.chris.bulleyeadmin.pojo.RoleMenu;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-24 15:10
 */
public class RoleMenuDto extends RoleMenu {

    private String menuName;

    private String menuIcon;

    private String menuPath;

    private String menuSort;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(String menuSort) {
        this.menuSort = menuSort;
    }
}
