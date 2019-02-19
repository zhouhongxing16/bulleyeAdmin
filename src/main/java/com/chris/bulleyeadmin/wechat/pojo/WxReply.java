package com.chris.bulleyeadmin.wechat.pojo;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;

    @Column(name = "graphic_id")
    private String graphicId;

    @Column(name = "num")
    private Long num;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created")
    private Long created;


    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return accountId
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return graphicId
     */
    public String getGraphicId() {
        return graphicId;
    }

    /**
     * @param graphicId
     */
    public void setGraphicId(String graphicId) {
        this.graphicId = graphicId == null ? null : graphicId.trim();
    }

    /**
     * @return num
     */
    public Long getNum() {
        return num;
    }

    /**
     * @param num
     */
    public void setNum(Long num) {
        this.num = num;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return created
     */
    public Long getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Long created) {
        this.created = created;
    }
}