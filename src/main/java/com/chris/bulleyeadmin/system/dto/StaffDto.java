package com.chris.bulleyeadmin.system.dto;

import com.chris.bulleyeadmin.system.pojo.Staff;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 17:22
 * @Description:
 */
public class StaffDto extends Staff {

    private String genderName;

    private String titleName;

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
}
