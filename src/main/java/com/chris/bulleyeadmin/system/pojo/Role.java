package com.chris.bulleyeadmin.system.pojo;

import javax.persistence.*;
import java.io.Serializable;

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
    @Column(name = "organization_id")
    private String organizationId;

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
    private String describe;

    /**
     * 状态
     */
    private Integer status;

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
     * 获取组织标识
     *
     * @return organizationId - 组织标识
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * 设置组织标识
     *
     * @param organizationId 组织标识
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }
}