package com.chris.bulleyeadmin.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Chris on 2019-05-27 14:19:44
 */
@Table(name = "b_attach_file")
public class AttachFiles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "biz_id")
    private String bizId;

    @Column(name = "organization_id")
    private String organizationId;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "download_count")
    private Integer downloadCount;

    @Column(name = "status")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "created")
    private Date created;


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
     * @return bizId
     */
    public String getBizId() {
        return bizId;
    }

    /**
     * @param bizId
     */
    public void setBizId(String bizId) {
        this.bizId = bizId == null ? null : bizId.trim();
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * @return downloadCount
     */
    public Integer getDownloadCount() {
        return downloadCount;
    }

    /**
     * @param downloadCount
     */
    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
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
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}