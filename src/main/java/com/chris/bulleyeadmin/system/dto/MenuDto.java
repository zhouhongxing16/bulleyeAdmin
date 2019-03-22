package com.chris.bulleyeadmin.system.dto;


import com.chris.bulleyeadmin.system.pojo.Menu;

import java.util.List;

public class MenuDto extends Menu {
    private boolean open;
    private boolean checked;
    private boolean isLeaf;
    private String value;
    private String key;
    private List<MenuDto> children;

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

    public boolean isChecked()
    {
        return this.checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public List<MenuDto> getChildren()
    {
        return this.children;
    }

    public void setChildren(List<MenuDto> children)
    {
        this.children = children;
    }

    public boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean leaf) {
       this.isLeaf = leaf;
    }
}
