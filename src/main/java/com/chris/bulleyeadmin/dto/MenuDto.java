package com.chris.bulleyeadmin.dto;


import com.chris.bulleyeadmin.pojo.Menu;

import java.util.List;

public class MenuDto extends Menu {
    private boolean open;
    private boolean checked;
    private String rId;
    private List<MenuDto> children;

    public String getrId()
    {
        return this.rId;
    }

    public void setrId(String rId)
    {
        this.rId = rId;
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
}
