package com.chris.bulleyeadmin.common.pojo;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Chris
 * @version 1.0
 * @date 2020/2/7 14:49
 */
@Table(name = "b_biz_file")
public class BizFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(name = "id")
    private String id;

    @ApiModelProperty(name = "组织Id")
    @Column(name = "organization_id")
    private String organizationId;

    @ApiModelProperty(name = "部门Id")
    @Column(name = "department_id")
    private String departmentId;

    /**
     * 文件大小
     */
    @ApiModelProperty(name = "文件大小")
    @Column(name = "size")
    public Long size;
    /**
     * 文件后缀（Suffix）
     */
    @ApiModelProperty(name = "文件后缀（Suffix）")
    @Column(name = "suffix")
    public String suffix;
    /**
     * 图片略缩图
     */
    @ApiModelProperty(name = "图片略缩图")
    @Column(name = "thumbnail")
    public String thumbnail;
    /**
     * 图片文件的宽
     */
    @ApiModelProperty(name = "图片文件的宽")
    @Column(name = "width")
    public Integer width;
    /**
     * 图片文件的高
     */
    @ApiModelProperty(name = "图片文件的高")
    @Column(name = "height")
    public Integer height;
    /**
     * 文件hash
     */
    @ApiModelProperty(name = "文件hash")
    @Column(name = "file_hash")
    private String fileHash;
    /**
     * 文件路径 （不带域名）
     */
    @ApiModelProperty(name = "文件路径 （不带域名）")
    @Column(name = "file_path")
    private String filePath;
    /**
     * 文件全路径 （带域名）
     */
    @ApiModelProperty(name = "件全路径 （带域名）")
    @Column(name = "full_file_path")
    private String fullFilePath;
    /**
     * 原始文件名
     */
    @ApiModelProperty(name = "原始文件名")
    @Column(name = "original_file_name")
    private String originalFileName;
    /**
     * 原始文件名
     */
    @ApiModelProperty(name = "bucketName")
    @Column(name = "bucket_name")
    private String bucketName;
    /**
     * 类型（AliOSS|qiniu|tencent|local）
     */
    @ApiModelProperty(name = "类型")
    @Column(name = "storage_type")
    private String storageType;
    /**
     * 文件上传开始的时间
     */
    @ApiModelProperty(name = "文件上传开始的时间")
    @Column(name = "upload_start_time")
    private Date uploadStartTime;
    /**
     * 文件上传结束的时间
     */
    @ApiModelProperty(name = "文件上传结束的时间")
    @Column(name = "upload_end_time")
    private Date uploadEndTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间")
    @Column(name = "created")
    private Date created;
    /**
     * 修改时间
     */
    @ApiModelProperty(name = "修改时间")
    @Column(name = "updated")
    private Date updated;
    /**
     * 创建人
     */
    @ApiModelProperty(name = "创建人")
    @Column(name = "user_id")
    private String userId;
    /**
     * 文件上传结束的时间
     */
    @ApiModelProperty(name = "状态")
    @Column(name = "status")
    private Integer status;

    public long getUseTime() {
        Date uploadEndTime = this.getUploadEndTime();
        Date uploadStartTime = this.getUploadStartTime();
        if (null == uploadStartTime || null == uploadEndTime) {
            return -1;
        }
        return uploadEndTime.getTime() - uploadStartTime.getTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFullFilePath() {
        return fullFilePath;
    }

    public void setFullFilePath(String fullFilePath) {
        this.fullFilePath = fullFilePath;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public Date getUploadStartTime() {
        return uploadStartTime;
    }

    public void setUploadStartTime(Date uploadStartTime) {
        this.uploadStartTime = uploadStartTime;
    }

    public Date getUploadEndTime() {
        return uploadEndTime;
    }

    public void setUploadEndTime(Date uploadEndTime) {
        this.uploadEndTime = uploadEndTime;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
