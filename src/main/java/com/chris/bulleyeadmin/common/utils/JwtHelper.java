package com.chris.bulleyeadmin.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.system.pojo.Role;
import com.chris.bulleyeadmin.system.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Chris
 * @Date: 2019-07-23 15:50
 * @Description:
 */
public class JwtHelper {
    /**
     * token 过期时间, 单位: 秒. 这个值表示 1 天
     */
    private static final long TOKEN_EXPIRED_TIME = 60 * 60 * 24 * 1000;

    /**
     * jwt 加密解密密钥
     */
    private static final String JWT_SECRET = "ucb-admin-chris-950602";

    public static final String jwtId = "tokenId";

    /**
     * 创建JWT
     */
    public static String createJWT(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512; //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();//生成JWT的时间
        long expMillis = nowMillis + TOKEN_EXPIRED_TIME;
        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() //这里其实就是new一个JwtBuilder，设置jwt的body
                //.setClaims(claims)          //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                //.setId(jwtId)                  //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                //.setIssuedAt(now)           //iat: jwt的签发时间
                .setSubject(subject)
                .setExpiration(new Date(expMillis)) //设置过期时间
                .signWith(signatureAlgorithm, secretKey);//设置签名使用的签名算法和签名使用的秘钥;
        return builder.compact();
    }

    /**
     * 验证jwt
     */
    public static Claims verifyJwt(String token) {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        Claims claims;
        try {
            claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(key)         //设置签名的秘钥
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }//设置需要解析的jwt
        return claims;

    }


    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        String stringKey = JWT_SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static User tokenToUser(String token) {
        SecretKey stringKey = generalKey();
        String jsonObject = Jwts.parser().setSigningKey(stringKey).parseClaimsJws(token.replace("Bearer ", "")).getBody().getSubject();
        JSONObject obj = JSON.parseObject(jsonObject);
        JSONArray ja = JSONArray.parseArray(obj.getString("authorities"));
        List<Role> roleList = JSONArray.parseArray(obj.getString("role"), Role.class);
        User user  = new User(obj.getString("username"), "", ja.toJavaList(GrantedAuthority.class));
        user.setStaffId(obj.getString("staffId"));
        user.setId(obj.getString("id"));
        user.setDepartmentId(obj.getString("departmentId"));
        user.setOrganizationId(obj.getString("organizationId"));
        user.setRole(roleList);
        return user;
    }

}
