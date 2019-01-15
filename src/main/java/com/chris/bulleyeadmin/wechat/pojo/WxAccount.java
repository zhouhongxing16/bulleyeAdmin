package com.chris.bulleyeadmin.wechat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "wx_account")
public class WxAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "account_pic")
    private String accountPic;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "appid")
    private String appid;

    @Column(name = "appsecret")
    private String appsecret;

    @Column(name = "token")
    private String token;

    @Column(name = "domain")
    private String domain;

    @Column(name = "access_token")
    private String accessToken;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date tokenTime;

    @Column(name = "menu_state")
    private String menuState;

    @Column(name = "adduserid")
    private Long adduserid;

    @Column(name = "addusername")
    private String addusername;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date addtime;

    @Column(name = "updateid")
    private Long updateid;

    @Column(name = "updatename")
    private String updatename;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatetime;

    @Column(name = "remark")
    private String remark;

    @Column(name = "main_account")
    private String mainAccount;

    @Column(name = "wx_partner")
    private String wxPartner;

    @Column(name = "wx_partnerkey")
    private String wxPartnerkey;

    @Column(name = "wx_file")
    private String wxFile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountPic() {
        return accountPic;
    }

    public void setAccountPic(String accountPic) {
        this.accountPic = accountPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(Date tokenTime) {
        this.tokenTime = tokenTime;
    }

    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    public Long getAdduserid() {
        return adduserid;
    }

    public void setAdduserid(Long adduserid) {
        this.adduserid = adduserid;
    }

    public String getAddusername() {
        return addusername;
    }

    public void setAddusername(String addusername) {
        this.addusername = addusername;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Long getUpdateid() {
        return updateid;
    }

    public void setUpdateid(Long updateid) {
        this.updateid = updateid;
    }

    public String getUpdatename() {
        return updatename;
    }

    public void setUpdatename(String updatename) {
        this.updatename = updatename;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMainAccount() {
        return mainAccount;
    }

    public void setMainAccount(String mainAccount) {
        this.mainAccount = mainAccount;
    }
}