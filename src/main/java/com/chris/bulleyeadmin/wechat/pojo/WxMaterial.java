package com.chris.bulleyeadmin.wechat.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


@Table(name = "wx_material")
public class WxMaterial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "media_id")
    private String mediaId;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "type")
    private String type;

    @Column(name = "title")
    private String title;

    @Column(name = "thumb_media_id")
    private String thumbMediaId;

    @Column(name = "thumb_file_id")
    private String thumbFileId;

    @Column(name = "author")
    private String author;

    @Column(name = "digest")
    private String digest;

    @Column(name = "show_cover_pic")
    private Boolean showCoverPic;

    @Column(name = "content")
    private String content;

    @Column(name = "content_source_url")
    private String contentSourceUrl;

    @Column(name = "need_open_comment")
    private Boolean needOpenComment;

    @Column(name = "only_fans_can_comment")
    private Boolean onlyFansCanComment;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "down_url")
    private String downUrl;

    @Column(name = "created")
    private Timestamp created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getThumbFileId() {
        return thumbFileId;
    }

    public void setThumbFileId(String thumbFileId) {
        this.thumbFileId = thumbFileId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Boolean getShowCoverPic() {
        return showCoverPic;
    }

    public void setShowCoverPic(Boolean showCoverPic) {
        this.showCoverPic = showCoverPic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }

    public Boolean getNeedOpenComment() {
        return needOpenComment;
    }

    public void setNeedOpenComment(Boolean needOpenComment) {
        this.needOpenComment = needOpenComment;
    }

    public Boolean getOnlyFansCanComment() {
        return onlyFansCanComment;
    }

    public void setOnlyFansCanComment(Boolean onlyFansCanComment) {
        this.onlyFansCanComment = onlyFansCanComment;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
