package com.chris.bulleyeadmin.system.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:17
 * @Description:
 */
@Table(name = "b_menu_function")
public class MenuFunction implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "function_id")
    private String functionId;

    /**
     * 创建时间
     */
    private Long created;

    @Column(name = "user_id")
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }
}
