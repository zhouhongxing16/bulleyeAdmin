package com.chris.bulleyeadmin.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "b_logs")
public class Logs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "org_id")
    private String orgId;

    private String optname;

    /**
     * 新增、删除、修改、查询、登录
     */
    private String opttype;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "staff_id")
    private String staffId;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date created;

    private Integer status;

    private String content;

    private static final long serialVersionUID = 1L;

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
     * @return org_id
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * @param orgId
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * @return optname
     */
    public String getOptname() {
        return optname;
    }

    /**
     * @param optname
     */
    public void setOptname(String optname) {
        this.optname = optname == null ? null : optname.trim();
    }

    /**
     * 获取新增、删除、修改、查询、登录
     *
     * @return opttype - 新增、删除、修改、查询、登录
     */
    public String getOpttype() {
        return opttype;
    }

    /**
     * 设置新增、删除、修改、查询、登录
     *
     * @param opttype 新增、删除、修改、查询、登录
     */
    public void setOpttype(String opttype) {
        this.opttype = opttype == null ? null : opttype.trim();
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return staff_id
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}