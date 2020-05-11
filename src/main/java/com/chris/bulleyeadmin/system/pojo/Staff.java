package com.chris.bulleyeadmin.system.pojo;

import java.util.Date;
import javax.persistence.*;
import java.math.BigDecimal;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-04-28 22:49
 */
@Table(name = "b_staff")
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "name")
    private String name;

    @Column(name = "gender_id")
    private String genderId;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "organization_id")
    private String organizationId;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "academic_id")
    private String academicId;

    @Column(name = "degree_id")
    private String degreeId;

    @Column(name = "position_id")
    private String positionId;

    @Column(name = "title_id")
    private String titleId;

    @Column(name = "type_id")
    private String typeId;

    @Column(name = "identify_type_id")
    private String identifyTypeId;

    @Column(name = "identify_no")
    private String identifyNo;

    @Column(name = "birth_province_id")
    private String birthProvinceId;

    @Column(name = "birth_city_id")
    private String birthCityId;

    @Column(name = "policy")
    private String policy;

    @Column(name = "nation_id")
    private String nationId;

    @Column(name = "join_date")
    private String joinDate;

    @Column(name = "remark")
    private String remark;

    @Column(name = "status")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "created")
    private Date created;

    @Column(name = "user_id")
    private String userId;


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
     * @return serialNo
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * @param serialNo
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo == null ? null : serialNo.trim();
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
     * @return genderId
     */
    public String getGenderId() {
        return genderId;
    }

    /**
     * @param genderId
     */
    public void setGenderId(String genderId) {
        this.genderId = genderId == null ? null : genderId.trim();
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * @return organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }

    /**
     * @return departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    /**
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * @return academicId
     */
    public String getAcademicId() {
        return academicId;
    }

    /**
     * @param academicId
     */
    public void setAcademicId(String academicId) {
        this.academicId = academicId == null ? null : academicId.trim();
    }

    /**
     * @return degreeId
     */
    public String getDegreeId() {
        return degreeId;
    }

    /**
     * @param degreeId
     */
    public void setDegreeId(String degreeId) {
        this.degreeId = degreeId == null ? null : degreeId.trim();
    }

    /**
     * @return positionId
     */
    public String getPositionId() {
        return positionId;
    }

    /**
     * @param positionId
     */
    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
    }

    /**
     * @return titleId
     */
    public String getTitleId() {
        return titleId;
    }

    /**
     * @param titleId
     */
    public void setTitleId(String titleId) {
        this.titleId = titleId == null ? null : titleId.trim();
    }

    /**
     * @return typeId
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    /**
     * @return identifyTypeId
     */
    public String getIdentifyTypeId() {
        return identifyTypeId;
    }

    /**
     * @param identifyTypeId
     */
    public void setIdentifyTypeId(String identifyTypeId) {
        this.identifyTypeId = identifyTypeId == null ? null : identifyTypeId.trim();
    }

    /**
     * @return identifyNo
     */
    public String getIdentifyNo() {
        return identifyNo;
    }

    /**
     * @param identifyNo
     */
    public void setIdentifyNo(String identifyNo) {
        this.identifyNo = identifyNo == null ? null : identifyNo.trim();
    }

    /**
     * @return birthProvinceId
     */
    public String getBirthProvinceId() {
        return birthProvinceId;
    }

    /**
     * @param birthProvinceId
     */
    public void setBirthProvinceId(String birthProvinceId) {
        this.birthProvinceId = birthProvinceId == null ? null : birthProvinceId.trim();
    }

    /**
     * @return birthCityId
     */
    public String getBirthCityId() {
        return birthCityId;
    }

    /**
     * @param birthCityId
     */
    public void setBirthCityId(String birthCityId) {
        this.birthCityId = birthCityId == null ? null : birthCityId.trim();
    }

    /**
     * @return policy
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * @param policy
     */
    public void setPolicy(String policy) {
        this.policy = policy == null ? null : policy.trim();
    }

    /**
     * @return nationId
     */
    public String getNationId() {
        return nationId;
    }

    /**
     * @param nationId
     */
    public void setNationId(String nationId) {
        this.nationId = nationId == null ? null : nationId.trim();
    }

    /**
     * @return joinDate
     */
    public String getJoinDate() {
        return joinDate;
    }

    /**
     * @param joinDate
     */
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate == null ? null : joinDate.trim();
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
