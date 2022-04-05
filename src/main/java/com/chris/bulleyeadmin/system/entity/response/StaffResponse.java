package com.chris.bulleyeadmin.system.entity.response;

import com.chris.bulleyeadmin.system.pojo.Staff;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:22
 * @Description:
 */
public class StaffResponse {

    private String genderName;

    private String titleName;

    private String id;

    private String serialNo;

    private String name;

    private String genderId;

    private String mobile;

    private String email;

    private String avatar;

    private String organizationId;

    private String departmentId;

    private String birthday;

    private String academicId;

    private String degreeId;

    private String positionId;

    private String titleId;

    private String typeId;

    private String identifyTypeId;

    private String identifyNo;

    private String birthProvinceId;

    private String birthCityId;

    private String policy;

    private String nationId;

    private String joinDate;

    private String remark;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;

    private String userId;

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenderId() {
        return genderId;
    }

    public void setGenderId(String genderId) {
        this.genderId = genderId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAcademicId() {
        return academicId;
    }

    public void setAcademicId(String academicId) {
        this.academicId = academicId;
    }

    public String getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getIdentifyTypeId() {
        return identifyTypeId;
    }

    public void setIdentifyTypeId(String identifyTypeId) {
        this.identifyTypeId = identifyTypeId;
    }

    public String getIdentifyNo() {
        return identifyNo;
    }

    public void setIdentifyNo(String identifyNo) {
        this.identifyNo = identifyNo;
    }

    public String getBirthProvinceId() {
        return birthProvinceId;
    }

    public void setBirthProvinceId(String birthProvinceId) {
        this.birthProvinceId = birthProvinceId;
    }

    public String getBirthCityId() {
        return birthCityId;
    }

    public void setBirthCityId(String birthCityId) {
        this.birthCityId = birthCityId;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getNationId() {
        return nationId;
    }

    public void setNationId(String nationId) {
        this.nationId = nationId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
