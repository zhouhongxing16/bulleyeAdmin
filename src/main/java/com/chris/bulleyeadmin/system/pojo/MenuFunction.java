package com.chris.bulleyeadmin.system.pojo;

import java.util.Date;
import javax.persistence.*;
import java.math.BigDecimal;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-04-28 22:42
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "created")
    private Date created;


    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return menuId
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }

    /**
     * @return functionId
     */
    public String getFunctionId() {
        return functionId;
    }

    /**
     * @param functionId
     */
    public void setFunctionId(String functionId) {
        this.functionId = functionId == null ? null : functionId.trim();
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

}