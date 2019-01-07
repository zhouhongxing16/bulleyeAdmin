package com.chris.bulleyeadmin.system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "b_accounts")
public class Account {

    private static final long serialVersionUID = 1L;


    /**
     * 唯一标识
     */
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
     * 状态
     */
    private Integer status;

    /**
     * 是否开通手机号登录
     */
    @Column(name = "mobile_login_flag")
    private Boolean mobileLoginFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date created;

    /**
     * 修改时间
     */
    private Date modified;

    @Column(name = "expired_date")
    private Date expiredDate;


    private List<Role> role;


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
     * 获取是否锁定
     *
     * @return account_locked - 是否锁定
     */
    public Boolean getAccountLocked() {
        return accountLocked;
    }

    /**
     * 设置是否锁定
     *
     * @param accountLocked 是否锁定
     */
    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    /**
     * 获取是否凭证过期
     *
     * @return credentials_expired - 是否凭证过期
     */
    public Boolean getCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * 设置是否凭证过期
     *
     * @param credentialsExpired 是否凭证过期
     */
    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     * 获取是否过期
     *
     * @return account_expired - 是否过期
     */
    public Boolean getAccountExpired() {
        return accountExpired;
    }

    /**
     * 设置是否过期
     *
     * @param accountExpired 是否过期
     */
    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    /**
     * 获取人员标识
     *
     * @return staff_id - 人员标识
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 设置人员标识
     *
     * @param staffId 人员标识
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
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
     * 获取微信OpenId
     *
     * @return wx_openid - 微信OpenId
     */
    public String getWxOpenid() {
        return wxOpenid;
    }

    /**
     * 设置微信OpenId
     *
     * @param wxOpenid 微信OpenId
     */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid == null ? null : wxOpenid.trim();
    }

    /**
     * 获取支付宝OpenId
     *
     * @return alipay_openid - 支付宝OpenId
     */
    public String getAlipayOpenid() {
        return alipayOpenid;
    }

    /**
     * 设置支付宝OpenId
     *
     * @param alipayOpenid 支付宝OpenId
     */
    public void setAlipayOpenid(String alipayOpenid) {
        this.alipayOpenid = alipayOpenid == null ? null : alipayOpenid.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
     * 获取是否开通手机号登录
     *
     * @return mobile_login_flag - 是否开通手机号登录
     */
    public Boolean getMobileLoginFlag() {
        return mobileLoginFlag;
    }

    /**
     * 设置是否开通手机号登录
     *
     * @param mobileLoginFlag 是否开通手机号登录
     */
    public void setMobileLoginFlag(Boolean mobileLoginFlag) {
        this.mobileLoginFlag = mobileLoginFlag;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取修改时间
     *
     * @return modified - 修改时间
     */
    public Date getModified() {
        return modified;
    }

    /**
     * 设置修改时间
     *
     * @param modified 修改时间
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * @return expired_date
     */
    public Date getExpiredDate() {
        return expiredDate;
    }

    /**
     * @param expiredDate
     */
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }



    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }



}