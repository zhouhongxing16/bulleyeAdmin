package com.chris.bulleyeadmin.system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "b_organizations")
public class Organization implements Serializable {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    private String pid;

    /**
     * 组织编码
     */
    private String code;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 所在地区
     */
    @Column(name = "area_id")
    private String areaId;

    /**
     * 组织简介
     */
    private String brief;

    /**
     * 联系人
     */
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 联系电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 试用结束日期
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 创建日期
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date created;

    /**
     * 域名
     */
    private String domain;

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
     * @return pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 获取组织编码
     *
     * @return code - 组织编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置组织编码
     *
     * @param code 组织编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取组织名称
     *
     * @return name - 组织名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组织名称
     *
     * @param name 组织名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取所在地区
     *
     * @return area_id - 所在地区
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 设置所在地区
     *
     * @param areaId 所在地区
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * 获取组织简介
     *
     * @return brief - 组织简介
     */
    public String getBrief() {
        return brief;
    }

    /**
     * 设置组织简介
     *
     * @param brief 组织简介
     */
    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    /**
     * 获取联系人
     *
     * @return contact_name - 联系人
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 设置联系人
     *
     * @param contactName 联系人
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     * 获取联系电话
     *
     * @return contact_phone - 联系电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置联系电话
     *
     * @param contactPhone 联系电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
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
     * 获取试用结束日期
     *
     * @return end_date - 试用结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置试用结束日期
     *
     * @param endDate 试用结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    /**
     * 获取域名
     *
     * @return domain - 域名
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置域名
     *
     * @param domain 域名
     */
    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }
}