package com.chris.bulleyeadmin;

import java.util.*;

/**
 * @Auther: Chris
 * @Date: 2019-02-14 17:20
 * @Description:
 */
public class Test {
    public static void main(String[] args){
        Map<String, String> map = new HashMap<String, String>();
        map.put("A", "ABC");
        map.put("K", "KK");
        map.put("L", "LV");
        List<String> mapValuesList = new ArrayList<String>(map.values());
        //数组-->Set
        System.out.println(mapValuesList);
    }
}
