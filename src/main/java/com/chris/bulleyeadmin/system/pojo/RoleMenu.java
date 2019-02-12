package com.chris.bulleyeadmin.system.pojo;

import javax.persistence.*;
import java.io.Serializable;

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
    private Long created;

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

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }
}