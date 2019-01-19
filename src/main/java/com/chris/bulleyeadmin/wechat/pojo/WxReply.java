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

    @Column(name = "type_id")
    private String typeId;

    @Column(name = "num")
    private Long num;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created")
    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}