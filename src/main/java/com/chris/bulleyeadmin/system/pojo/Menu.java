package com.chris.bulleyeadmin.system.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "b_menu")
public class Menu implements Serializable {
    /**
     * 唯一标识
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    /**
     * 父菜单id
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String title;

    /**
     * 路径
     */
    private String path;

    /**
     * 是否显示（0：不显示，1：显示）
     */
    private Integer status;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Long created;

    private static final long serialVersionUID = 1L;

    /**
     * 获取唯一标识
     *
     * @return id - 唯一标识
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一标识
     *
     * @param id 唯一标识
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取代码
     *
     * @return code - 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取路径
     *
     * @return path - 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取是否显示（0：不显示，1：显示）
     *
     * @return status - 是否显示（0：不显示，1：显示）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置是否显示（0：不显示，1：显示）
     *
     * @param status 是否显示（0：不显示，1：显示）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取显示顺序
     *
     * @return sort - 显示顺序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置显示顺序
     *
     * @param sort 显示顺序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }
}