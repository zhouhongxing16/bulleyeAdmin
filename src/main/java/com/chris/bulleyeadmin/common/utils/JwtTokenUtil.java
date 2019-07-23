package com.chris.bulleyeadmin.common.utils;

import com.chris.bulleyeadmin.system.pojo.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-07-17 11:54
 * @Description:
 */
public class JwtTokenUtil {
    public static String SECRET = "BulleyeAdminSecret";


    public static String createToken(User user) throws Exception {

        String token = Jwts.builder()
                .setSubject(user.toString())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "BulleyeAdminSecret")
                .compact();

        return token;
    }




}
