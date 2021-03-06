package com.chris.bulleyeadmin.wechat.pojo;

/**
 * @Auther: Chris
 * @Date: 2019-01-11 17:47
 * @Description:
 */

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chris on Fri Jan 11 2019 17:46:16 GMT+0800 (中国标准时间)
 */
@Table(name = "wx_member")
public class WxMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "source_id")
    private String sourceId;


    @Column(name = "subscribe")
    private Boolean subscribe;


    @Column(name = "open_id")
    private String openId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "sex_desc")
    private String sexDesc;

    @Column(name = "sex")
    private String sex;

    @Column(name = "language")
    private String language;


    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "province")
    private String province;

    @Column(name = "head_img_url")
    private String headImgUrl;

    @Column(name = "subscribe_time")
    private Long subscribeTime;

    @Column(name = "union_id")
    private String unionId;

    @Column(name = "remark")
    private String remark;

    @Column(name = "group_id")
    private String groupId;

    //@Column(name = "tag_ids")
    @Transient
    private Long[] tagIds;

   // @Column(name = "privileges")
    @Transient
    private String[] privileges;

    @Column(name = "subscribe_scene")
    private String subscribeScene;

    @Column(name = "qr_scene")
    private String qrScene;

    @Column(name = "qr_scene_str")
    private String qrSceneStr;

    @Column(name = "unsubscribe_time")
    private Long unsubscribeTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "created")
    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSexDesc() {
        return sexDesc;
    }

    public void setSexDesc(String sexDesc) {
        this.sexDesc = sexDesc;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Long[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(Long[] tagIds) {
        this.tagIds = tagIds;
    }

    public String[] getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String[] privileges) {
        this.privileges = privileges;
    }

    public String getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    public String getQrScene() {
        return qrScene;
    }

    public void setQrScene(String qrScene) {
        this.qrScene = qrScene;
    }

    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
    }

    public Long getUnsubscribeTime() {
        return unsubscribeTime;
    }

    public void setUnsubscribeTime(Long unsubscribeTime) {
        this.unsubscribeTime = unsubscribeTime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
