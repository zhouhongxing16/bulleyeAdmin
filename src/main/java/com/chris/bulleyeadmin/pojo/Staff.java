package com.chris.bulleyeadmin.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "b_staff")
public class Staff implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Column(name = "serial_no")
    private String serialNo;

    private String name;

    @Column(name = "gender_id")
    private String genderId;

    private String birthday;

    @Column(name = "major_id")
    private String majorId;

    @Column(name = "academic_id")
    private String academicId;

    @Column(name = "degree_id")
    private String degreeId;

    /**
     * 头像
     */
    private String avator;

    /**
     * 冗余字段，便于查询
     */
    @Column(name = "organization_id")
    private String organizationId;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "position_id")
    private String positionId;

    @Column(name = "title_id")
    private String titleId;

    @Column(name = "type_id")
    private String typeId;

    private String mobile;

    @Column(name = "identify_type_id")
    private String identifyTypeId;

    @Column(name = "identify_no")
    private String identifyNo;

    private String email;

    private String remark;

    private Integer status;

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date created;

    @Column(name = "birth_province_id")
    private String birthProvinceId;

    @Column(name = "birth_city_id")
    private String birthCityId;

    /**
     * 党员、共青团员、群众
     */
    private String policy;

    private BigDecimal weight;

    private Integer height;

    @Column(name = "health_status")
    private String healthStatus;

    /**
     * 已婚、未婚、离异
     */
    @Column(name = "marry_status_id")
    private String marryStatusId;


    @Column(name = "nation_id")
    private String nationId;

    @Column(name = "vmnet_no")
    private String vmnetNo;

    @Column(name = "join_date")
    private String joinDate;

    private static final long serialVersionUID = 1L;

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
     * @return serial_no
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
     * @return gender_id
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
     * @return major_id
     */
    public String getMajorId() {
        return majorId;
    }

    /**
     * @param majorId
     */
    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    /**
     * @return academic_id
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
     * @return degree_id
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
     * 获取头像
     *
     * @return avator - 头像
     */
    public String getAvator() {
        return avator;
    }

    /**
     * 设置头像
     *
     * @param avator 头像
     */
    public void setAvator(String avator) {
        this.avator = avator == null ? null : avator.trim();
    }

    /**
     * 获取冗余字段，便于查询
     *
     * @return org_id - 冗余字段，便于查询
     */
    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 设置冗余字段，便于查询
     *
     * @param organizationId 冗余字段，便于查询
     */


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return position_id
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
     * @return title_id
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
     * @return type_id
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
     * @return identify_type_id
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
     * @return identify_no
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

    /**
     * @return birth_province_id
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
     * @return birth_city_id
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
     * 获取党员、共青团员、群众
     *
     * @return policy - 党员、共青团员、群众
     */
    public String getPolicy() {
        return policy;
    }

    /**
     * 设置党员、共青团员、群众
     *
     * @param policy 党员、共青团员、群众
     */
    public void setPolicy(String policy) {
        this.policy = policy == null ? null : policy.trim();
    }

    /**
     * @return weight
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * @param weight
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * @return height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return health_status
     */
    public String getHealthStatus() {
        return healthStatus;
    }

    /**
     * @param healthStatus
     */
    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus == null ? null : healthStatus.trim();
    }

    /**
     * 获取已婚、未婚、离异
     *
     * @return marry_status_id - 已婚、未婚、离异
     */
    public String getMarryStatusId() {
        return marryStatusId;
    }

    /**
     * 设置已婚、未婚、离异
     *
     * @param marryStatusId 已婚、未婚、离异
     */
    public void setMarryStatusId(String marryStatusId) {
        this.marryStatusId = marryStatusId == null ? null : marryStatusId.trim();
    }


    /**
     * @return nation_id
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
     * @return vmnet_no
     */
    public String getVmnetNo() {
        return vmnetNo;
    }

    /**
     * @param vmnetNo
     */
    public void setVmnetNo(String vmnetNo) {
        this.vmnetNo = vmnetNo == null ? null : vmnetNo.trim();
    }

    /**
     * @return join_date
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
}