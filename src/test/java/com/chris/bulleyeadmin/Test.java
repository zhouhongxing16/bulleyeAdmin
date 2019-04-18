package com.chris.bulleyeadmin;

import com.chris.bulleyeadmin.common.utils.IPUtils;
import org.springframework.http.HttpStatus;

/**
 * @Auther: Chris
 * @Date: 2019-02-14 17:20
 * @Description:
 */
public class Test {
    public static void main(String[] args){
        System.out.println(IPUtils.getLocationByIP("218.88.113.146"));
    }
}
