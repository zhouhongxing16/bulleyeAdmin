package com.chris.bulleyeadmin.system.dto;

import com.chris.bulleyeadmin.system.pojo.DictionaryType;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 18:01
 * @Description:
 */
public class DictionaryTypeDto extends DictionaryType {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
