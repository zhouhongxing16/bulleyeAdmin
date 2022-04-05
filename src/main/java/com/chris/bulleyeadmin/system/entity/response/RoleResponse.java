package com.chris.bulleyeadmin.system.entity.response;

import com.chris.bulleyeadmin.system.pojo.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:21
 * @Description:
 */
public class RoleResponse  {

    private String id;

    private String organizationId;

    private String name;

    private String code;

    private String dataAuthFlag;

    private String remark;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDataAuthFlag() {
        return dataAuthFlag;
    }

    public void setDataAuthFlag(String dataAuthFlag) {
        this.dataAuthFlag = dataAuthFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
