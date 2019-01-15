package com.chris.bulleyeadmin.wechat.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: Chris
 * @Date: 2019-01-11 17:56
 * @Description:
 */
@Table(name = "wx_reply")
public class WxReply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "title")
    private String title;

    @Column(name = "pic")
    private String pic;

    @Column(name = "url")
    private String url;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;

    @Column(name = "num")
    private Long num;

    @Column(name = "created")
    private Date created;


}