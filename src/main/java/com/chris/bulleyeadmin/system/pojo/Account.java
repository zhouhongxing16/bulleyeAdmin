package com.chris.bulleyeadmin.system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "b_accounts")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    /**
     * 账户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否锁定
     */
    @Column(name = "account_locked")
    private Boolean accountLocked;

    /**
     * 是否凭证过期
     */
    @Column(name = "credentials_expired")
    private Boolean credentialsExpired;

    /**
     * 是否过期
     */
    @Column(name = "account_expired")
    private Boolean accountExpired;

    /**
     * 人员标识
     */
    @Column(name = "staff_id")
    private String staffId;

    /**
     * 组织标识
     */
    @Column(name = "organization_id")
    private String organizationId;

    /**
     * 微信OpenId
     */
    @Column(name = "wx_openid")
    private String wxOpenid;

    /**
     * 支付宝OpenId
     */
    @Column(name = "alipay_openid")
    private String alipayOpenid;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否开通手机号登录
     */
    @Column(name = "mobile_login_flag")
    private Boolean mobileLoginFlag;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modified;

    @Column(name = "expired_date")
    private Long expiredDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Column(name = "last_login_ip")
    private String lastLoginIp;

    private Integer status;

    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date created;


    private List<Role> role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Boolean getCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public Boolean getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getAlipayOpenid() {
        return alipayOpenid;
    }

    public void setAlipayOpenid(String alipayOpenid) {
        this.alipayOpenid = alipayOpenid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getMobileLoginFlag() {
        return mobileLoginFlag;
    }

    public void setMobileLoginFlag(Boolean mobileLoginFlag) {
        this.mobileLoginFlag = mobileLoginFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Long getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Long expiredDate) {
        this.expiredDate = expiredDate;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
}