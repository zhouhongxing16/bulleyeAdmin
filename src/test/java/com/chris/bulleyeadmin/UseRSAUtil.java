package com.chris.bulleyeadmin;



import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/**
 * RSA加解密
 * @author jiafuwei
 * created at 2017/6/8
 */
public class UseRSAUtil {

    /** *//**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** *//**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static final String KEY_ALGORITHM = "RSA";
    private static KeyFactory keyFactory = null;
    public static String PrivateKey = "私钥。。。";
    public static String publicKey="公钥。。。";

    static {
        try {
            keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     * @param dataStr 要解密的数据
     * @return 解密后的原数据
     * @throws Exception
     */
    public static String decrypt(String dataStr) throws Exception{
        //要加密的数据
        System.out.println("要解密的数据:"+dataStr);
        //对私钥解密
        Key decodePrivateKey = getPrivateKeyFromBase64KeyEncodeStr(PrivateKey);
        //Log.i("机密",""+decodePrivateKey);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, decodePrivateKey);
        byte[] encodedData = Base64.decode(dataStr);
        byte[] decodedData = cipher.doFinal(encodedData);
        String decodedDataStr = new String(decodedData,"utf-8");
        System.out.println("私钥解密后的数据:"+decodedDataStr);
        return decodedDataStr;
    }

    public  static Key getPrivateKeyFromBase64KeyEncodeStr(String keyStr) throws Base64DecodingException {
        byte[] keyBytes = Base64.decode(keyStr);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        Key privateKey=null;
        try {
            privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return privateKey;
    }

    /**
     * 获取base64加密后的字符串的原始公钥
     * @param keyStr
     * @return
     */
    public static Key getPublicKeyFromBase64KeyEncodeStr(String keyStr) throws Base64DecodingException {
        byte[] keyBytes = Base64.decode(keyStr);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        Key publicKey = null;
        try {
            publicKey = keyFactory.generatePublic(x509KeySpec);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * 公钥加密方法
     * @param dataStr 要加密的数据
     * @param dataStr 公钥base64字符串
     * @return 加密后的base64字符串
     * @throws Exception
     */
    public static String encryptPublicKey(String dataStr) throws Exception{
        //要加密的数据
        System.out.println("要加密的数据:"+dataStr);
        byte[] data = dataStr.getBytes();
        // 对公钥解密
        Key decodePublicKey = getPublicKeyFromBase64KeyEncodeStr(publicKey);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, decodePublicKey);
        byte[] encodedData = cipher.doFinal(data);
        String encodedDataStr = new String(Base64.encode(encodedData));
        System.out.println("公钥加密后的数据:"+encodedDataStr);
        return encodedDataStr;
    }

    /**
     * 使用公钥进行分段加密
     * @param dataStr 要加密的数据
     * @return 公钥base64字符串
     * @throws Exception
     */
    public static String encryptByPublicKey(String dataStr)
            throws Exception {
        //要加密的数据
        System.out.println("要加密的数据:"+dataStr);
        byte[] data = dataStr.getBytes();
        // 对公钥解密
        Key decodePublicKey = getPublicKeyFromBase64KeyEncodeStr(publicKey);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, decodePublicKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        String encodedDataStr = new String(Base64.encode(encryptedData));
        System.out.println("公钥加密后的数据:"+encodedDataStr);
        return encodedDataStr;
    }

    /**
     * 使用私钥进行分段解密
     * @param dataStr 使用base64处理过的密文
     * @return 解密后的数据
     * @throws Exception
     */
    public static String decryptByPrivateKey(String dataStr)
            throws Exception {

        byte[] encryptedData = Base64.decode(dataStr);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key decodePrivateKey = getPrivateKeyFromBase64KeyEncodeStr(PrivateKey);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, decodePrivateKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        String decodedDataStr = new String(decryptedData,"utf-8");
        System.out.println("私钥解密后的数据:"+decodedDataStr);
        return decodedDataStr;
    }


    public static void main(String[] args) throws Exception {

    }
}