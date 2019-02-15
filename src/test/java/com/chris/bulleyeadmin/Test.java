package com.chris.bulleyeadmin;

import com.alibaba.fastjson.JSON;
import com.chris.bulleyeadmin.system.pojo.User;

/**
 * @Auther: Chris
 * @Date: 2019-02-14 17:20
 * @Description:
 */
public class Test {
    public static void main(String[] args){
        String json = "{\"password\":null,\"username\":\"zhx\",\"authorities\":[{\"authority\":\"super\"}],\"accountNonExpired\":true,\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"enabled\":true,\"id\":\"4081fe65-125a-11e9-97db-382c4a232da1\",\"organizationId\":null,\"staffId\":\"218e2f1a-13e5-11e9-97db-382c4a232da1\",\"departmentId\":null,\"role\":[{\"id\":\"2\",\"organizationId\":\"super\",\"code\":\"super\",\"name\":\"super\",\"dataAuthFlag\":\"personal\",\"describe\":null,\"status\":0,\"created\":20180731115226}],\"roleName\":\"(super)\"}";
        User user = JSON.parseObject(json,User.class);
        System.out.println(user.getUsername());
    }
}
