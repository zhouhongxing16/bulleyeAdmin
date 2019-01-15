package com.chris.bulleyeadmin.wechat.pojo;

/**
 * @Auther: Chris
 * @Date: 2019-01-11 17:47
 * @Description:
 */

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Chris on Fri Jan 11 2019 17:46:16 GMT+0800 (中国标准时间)
 */
@Table(name = "wx_member")
public class WxMember implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "subscribe")
    private String subscribe;

    @Column(name = "openid")
    private String openid;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "sex")
    private String sex;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "province")
    private String province;

    @Column(name = "headimgurl")
    private String headimgurl;

    @Column(name = "createTime")
    private String createTime;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "code")
    private String code;

}
