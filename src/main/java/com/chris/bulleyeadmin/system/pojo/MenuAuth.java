package com.chris.bulleyeadmin.system.pojo;

import javax.persistence.*;
import java.io.Serializable;
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-03-17 21:00
 */
@Table(name = "b_menu_auth")
public class MenuAuth implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "menu_id")
    private String menuId;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "url")
    private String url;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "created")
    private Long created;
    
    
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
    
    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    
    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * @return created
     */
    public Long getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Long created) {
        this.created = created;
    }
    
}