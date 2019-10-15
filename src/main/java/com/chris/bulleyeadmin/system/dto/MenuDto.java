package com.chris.bulleyeadmin.system.dto;


import com.chris.bulleyeadmin.system.pojo.Menu;
import com.chris.bulleyeadmin.system.pojo.MenuAuth;

import java.util.List;

public class MenuDto extends Menu {
    private boolean open;
    private String value;
    private String key;
    private Boolean isLeaf;

    private List<MenuDto> children;

    private List<MenuAuth> authList;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isOpen()
    {
        return this.open;
    }

    public void setOpen(boolean open)
    {
        this.open = open;
    }



    public List<MenuDto> getChildren()
    {
        return this.children;
    }

    public void setChildren(List<MenuDto> children)
    {
        this.children = children;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean leaf) {
        this.isLeaf = leaf;
    }

    public List<MenuAuth> getAuthList() {
        return authList;
    }

    public void setAuthList(List<MenuAuth> authList) {
        this.authList = authList;
    }
}
