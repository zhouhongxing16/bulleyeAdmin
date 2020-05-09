package com.chris.bulleyeadmin.system.pojo;

import java.util.Date;
import javax.persistence.*;
import java.math.BigDecimal;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-04-28 22:44
 */
@Table(name = "b_dictionary_data")
public class DictionaryData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    @ApiModelProperty(value = "唯一标识")
    private String id;

    @ApiModelProperty(value = "类型标识")
    @Column(name = "type_id")
    private String typeId;

    @ApiModelProperty(value = "字典编码")
    @Column(name = "code")
    private String code;

    @ApiModelProperty(value = "字典名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "字典备注")
    @Column(name = "remark")
    private String remark;

    @ApiModelProperty(value = "状态")
    @Column(name = "status")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    @Column(name = "created")
    private Date created;

    @ApiModelProperty(value = "创建人")
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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
