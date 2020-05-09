package com.chris.bulleyeadmin.system.dto;

import com.chris.bulleyeadmin.system.pojo.DictionaryData;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 18:01
 * @Description:
 */
public class DictionaryDataDto extends DictionaryData {

    private String typeName;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
