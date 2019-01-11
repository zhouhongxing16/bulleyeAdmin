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
    private Long accountId;

    @Column(name = "reply_keyword")
    private String replyKeyword;

    @Column(name = "reply_title")
    private String replyTitle;

    @Column(name = "reply_pic")
    private String replyPic;

    @Column(name = "reply_url")
    private String replyUrl;

    @Column(name = "reply_content")
    private String replyContent;

    @Column(name = "reply_type")
    private String replyType;

    @Column(name = "reply_num")
    private Long replyNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return accountId
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * @return replyKeyword
     */
    public String getReplyKeyword() {
        return replyKeyword;
    }

    /**
     * @param replyKeyword
     */
    public void setReplyKeyword(String replyKeyword) {
        this.replyKeyword = replyKeyword == null ? null : replyKeyword.trim();
    }

    /**
     * @return replyTitle
     */
    public String getReplyTitle() {
        return replyTitle;
    }

    /**
     * @param replyTitle
     */
    public void setReplyTitle(String replyTitle) {
        this.replyTitle = replyTitle == null ? null : replyTitle.trim();
    }

    /**
     * @return replyPic
     */
    public String getReplyPic() {
        return replyPic;
    }

    /**
     * @param replyPic
     */
    public void setReplyPic(String replyPic) {
        this.replyPic = replyPic == null ? null : replyPic.trim();
    }

    /**
     * @return replyUrl
     */
    public String getReplyUrl() {
        return replyUrl;
    }

    /**
     * @param replyUrl
     */
    public void setReplyUrl(String replyUrl) {
        this.replyUrl = replyUrl == null ? null : replyUrl.trim();
    }

    /**
     * @return replyContent
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * @param replyContent
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    /**
     * @return replyType
     */
    public String getReplyType() {
        return replyType;
    }

    /**
     * @param replyType
     */
    public void setReplyType(String replyType) {
        this.replyType = replyType == null ? null : replyType.trim();
    }

    /**
     * @return replyNum
     */
    public Long getReplyNum() {
        return replyNum;
    }

    /**
     * @param replyNum
     */
    public void setReplyNum(Long replyNum) {
        this.replyNum = replyNum;
    }


}