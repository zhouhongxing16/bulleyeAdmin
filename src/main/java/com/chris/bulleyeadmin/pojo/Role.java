package com.chris.bulleyeadmin.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "b_roles")
public class Role implements Serializable {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    /**
     * 组织标识
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 角色代码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 个人（pesonal），部门（department）,组织（organization）,系统（system）
     */
    @Column(name = "data_auth_flag")
    private String dataAuthFlag;

    /**
     * 角色描述
     */
    private String describle;

    /**
     * 状态
     */
    private Integer status;

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
     * 获取组织标识
     *
     * @return org_id - 组织标识
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置组织标识
     *
     * @param orgId 组织标识
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 获取角色代码
     *
     * @return code - 角色代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置角色代码
     *
     * @param code 角色代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取个人（pesonal），部门（department）,组织（organization）,系统（system）
     *
     * @return data_auth_flag - 个人（pesonal），部门（department）,组织（organization）,系统（system）
     */
    public String getDataAuthFlag() {
        return dataAuthFlag;
    }

    /**
     * 设置个人（pesonal），部门（department）,组织（organization）,系统（system）
     *
     * @param dataAuthFlag 个人（pesonal），部门（department）,组织（organization）,系统（system）
     */
    public void setDataAuthFlag(String dataAuthFlag) {
        this.dataAuthFlag = dataAuthFlag == null ? null : dataAuthFlag.trim();
    }

    /**
     * 获取角色描述
     *
     * @return describle - 角色描述
     */
    public String getDescrible() {
        return describle;
    }

    /**
     * 设置角色描述
     *
     * @param describle 角色描述
     */
    public void setDescrible(String describle) {
        this.describle = describle == null ? null : describle.trim();
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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