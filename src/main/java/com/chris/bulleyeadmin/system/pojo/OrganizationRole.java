package com.chris.bulleyeadmin.system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.xml.internal.ws.spi.db.DatabindingException;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Auther: Chris
 * @Date: 2019-01-08 10:57
 * @Description:
 */
public class OrganizationRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "organization_id")
    private String organizationId;

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

    @Column(name = "user_id")
    private String userId;

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

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
