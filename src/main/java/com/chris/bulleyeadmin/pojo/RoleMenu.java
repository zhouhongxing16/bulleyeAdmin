package com.chris.bulleyeadmin.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "b_role_menu")
public class RoleMenu implements Serializable {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    /**
     * 菜单外键
     */
    @Column(name = "menu_id")
    private String menuId;

    /**
     * 角色外键
     */
    @Column(name = "role_id")
    private String roleId;

    /**
     * 创建日期
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date created;

    private static final long serialVersionUID = 1L;

    /**
     * 获取唯一标识
     *
     * @return id - 唯一标识
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取菜单外键
     *
     * @return menu_id - 菜单外键
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单外键
     *
     * @param menuId 菜单外键
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * 获取角色外键
     *
     * @return role_id - 角色外键
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色外键
     *
     * @param roleId 角色外键
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    /**
     * 获取创建日期
     *
     * @return created - 创建日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建日期
     *
     * @param created 创建日期
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}