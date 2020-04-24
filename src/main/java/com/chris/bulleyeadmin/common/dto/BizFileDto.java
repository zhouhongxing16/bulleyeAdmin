package com.chris.bulleyeadmin.common.dto;
import com.chris.bulleyeadmin.common.pojo.BizFile;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2020-04-22 23:50
 */
public class BizFileDto extends BizFile {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
