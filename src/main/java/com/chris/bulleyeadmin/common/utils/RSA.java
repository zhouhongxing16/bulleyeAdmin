package com.chris.bulleyeadmin.common.utils;


import com.chris.bulleyeadmin.system.pojo.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Chris
 * @date 2021-12-14 17:55
 * JAVA实现RSA加密,非对称加密算法
 */
public class RSA {



        public static final String KEY_ALGORITHM = "RSA";
        public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

        private static final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDt2wPaIt7KriNjuMlsYJo07Cft\n" +
                "PmxkPpfNMQ4d7spQHvfjQyVa8t6AweH7kmoLGzJSKPvdD4xIw4ElGBid5l8i6U/J\n" +
                "EMpwiMBBq6PA7Or1C3PwfC1TEvm+VywTJgLkHg3f2gqRQ5r+mZ+7tiharLNxSWAo\n" +
                "4hrDwvzWgV7O6Vj5fQIDAQAB\n" +
                "-----END PUBLIC KEY-----";
        private static final String PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n" +
                "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO3bA9oi3squI2O4\n" +
                "yWxgmjTsJ+0+bGQ+l80xDh3uylAe9+NDJVry3oDB4fuSagsbMlIo+90PjEjDgSUY\n" +
                "GJ3mXyLpT8kQynCIwEGro8Ds6vULc/B8LVMS+b5XLBMmAuQeDd/aCpFDmv6Zn7u2\n" +
                "KFqss3FJYCjiGsPC/NaBXs7pWPl9AgMBAAECgYBiwQ4mYVfy+r4Cx1QJxUoBMxOY\n" +
                "stQ6AqrwLK/Zya0B+vmcx+IYksHC37y9wJp6pf4MkquPqsbp5xIVnp2J4X4ZqaJo\n" +
                "es9eBKVXaM43KI3Rf4sHA2MMyiAGeJye071ngwkPT96kBjxQWtpOemxnlUax7w31\n" +
                "A09/6BDSNroKYCbImQJBAPycxWTalt2nEH3jJHwbeILDcERWHtSbAowCYg+kmY2F\n" +
                "fBaFRJPxNsMADefuC+LyvITiXdEuIBD9/genEHDWS+sCQQDxC5VDWwpsDMqbpRn/\n" +
                "JiLvbFeDjt8Rj15wOxCUCtPr9RI4/kXFsbwHhZr7ar3dlTWlFmUzKnDIGSE5iCpO\n" +
                "k343AkEAtQfMVkhoUZRGN1EudlphdKSGshJdqdMkch3LFJbLDCabFLCQwszch8Zk\n" +
                "MM/haNS30StAPIYnqMW85NMO7y5D3QJBAIut86PhaMuuKeZBqSvilJH7TUcuDnOl\n" +
                "icstXvD6yU1NaW7fYd0qnlQZhvK0KyvxH/H3ZVNd/5bkgQ1Oq313GMcCQCBGsAyk\n" +
                "UvNEWZUH062wscUTc0JOXzzPyBKR5ey2ak/F/cSravtZjupfzvMGgjUMwjpOu8p3\n" +
                "cB+EgR8jzpEmzNo=\n" +
                "-----END PRIVATE KEY-----\n";

        /**
         * 初始化密钥
         *
         * @return
         * @throws Exception
         */
        public static Map<String, Object> initKey() throws Exception {
            KeyPairGenerator keyPairGen = KeyPairGenerator
                    .getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(1024);
            KeyPair keyPair = keyPairGen.generateKeyPair();

            // 公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//		logger.info("------" + publicKey);
            // 私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//		logger.info("------" + publicKey);

           Map<String, Object> keyMap = new HashMap<String, Object>(2);

            keyMap.put(PUBLIC_KEY, publicKey);
            keyMap.put(PRIVATE_KEY, privateKey);
            return keyMap;
        }

        /**
         * 取得私钥
         *
         * @param keyMap
         * @return
         * @throws Exception
         */
        public static String getPrivateKey(Map<String, Object> keyMap)
                throws Exception {
            Key key = (Key) keyMap.get(PRIVATE_KEY);
            return encryptBASE64(key.getEncoded());
        }

        /**
         * 取得公钥
         *
         * @param keyMap
         * @return
         * @throws Exception
         */
        public static String getPublicKey(Map<String, Object> keyMap)
                throws Exception {
            Key key = (Key) keyMap.get(PUBLIC_KEY);
            return encryptBASE64(key.getEncoded());
        }


        /**
         * 解密&lt;br&gt;
         * 用私钥解密
         *
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public static byte[] decryptByPrivateKey(byte[] data, String key)
                throws Exception {
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(key);

            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            return cipher.doFinal(data);
        }

        /**
         * 解密&lt;br&gt;
         * 用公钥解密
         *
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public static byte[] decryptByPublicKey(byte[] data, String key)
                throws Exception {
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(key);

            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);

            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);

            return cipher.doFinal(data);
        }

        /**
         * 加密&lt;br&gt;
         * 用公钥加密
         *
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public static byte[] encryptByPublicKey(byte[] data, String key)
                throws Exception {
            // 对公钥解密
            byte[] keyBytes = decryptBASE64(key);

            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);

            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return cipher.doFinal(data);
        }

        /**
         * 加密&lt;br&gt;
         * 用私钥加密
         *
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public static byte[] encryptByPrivateKey(byte[] data, String key)
                throws Exception {
            // 对密钥解密
            byte[] keyBytes = decryptBASE64(key);

            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);

            return cipher.doFinal(data);
        }

        /**
         * 用私钥对信息生成数字签名
         *
         * @param data
         *            加密数据
         * @param privateKey
         *            私钥
         *
         * @return
         * @throws Exception
         */
        public static String sign(byte[] data, String privateKey) throws Exception {
            // 解密由base64编码的私钥
            byte[] keyBytes = decryptBASE64(privateKey);

            // 构造PKCS8EncodedKeySpec对象
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 用私钥对信息生成数字签名
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(priKey);
            signature.update(data);

            return encryptBASE64(signature.sign());
        }

        /**
         * 校验数字签名
         *
         * @param data
         *            加密数据
         * @param publicKey
         *            公钥
         * @param sign
         *            数字签名
         *
         * @return 校验成功返回true 失败返回false
         * @throws Exception
         *
         */
        public static boolean verify(byte[] data, String publicKey, String sign)
                throws Exception {

            // 解密由base64编码的公钥
            byte[] keyBytes = decryptBASE64(publicKey);

            // 构造X509EncodedKeySpec对象
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

            // KEY_ALGORITHM 指定的加密算法
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            // 取公钥匙对象
            PublicKey pubKey = keyFactory.generatePublic(keySpec);

            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(pubKey);
            signature.update(data);

            // 验证签名是否正常
            return signature.verify(decryptBASE64(sign));
        }

        private static byte[] decryptBASE64(String key) throws Exception {
            return (new BASE64Decoder()).decodeBuffer(key);
        }

        private static String encryptBASE64(byte[] key) throws Exception {
            return (new BASE64Encoder()).encodeBuffer(key);
        }

        public static void main(String[] args) {
           Map<String, Object> keyMap;
            try {
                //取得公钥和么私钥
                keyMap = initKey();
                String publicKey = getPublicKey(keyMap);
                Logger.info("字符类型公钥:" + publicKey);

                String privateKey = getPrivateKey(keyMap);
                Logger.info("字符类型私钥:" + privateKey);


                System.err.println("公钥加密——私钥解密");
                String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
                System.out.println("\r加密前文字：\r\n" + source);
                byte[] data = source.getBytes();
                byte[] encodedData = encryptByPublicKey(data, publicKey);
                System.out.println("加密后文字：\r\n" + encryptBASE64(encodedData));
                byte[] b = "ixxDF7/T0SydocTrSBjh1aNkpINfsL9gLiWQ+R3siXx7/E5RQ7B7W+WEztZ5QbqxzzfTDtDv/+jRrhyOF3O7Hw==".getBytes(StandardCharsets.UTF_8);

                byte[] decodedData = decryptByPrivateKey(encodedData, privateKey);
                 String target = new String(decodedData);
                System.out.println("解密后文字: \r\n" + target);

                System.err.println("私钥加密——公钥解密");
                String source1 = "这是一行测试RSA数字签名的无意义文字";
                System.out.println("原文字：\r\n" + source1);
                byte[] data1 = source1.getBytes();
                byte[] encodedData1 = encryptByPrivateKey(data1, privateKey);
                System.out.println("加密后：\r\n" + new String(encodedData1));
                byte[] decodedData1 = decryptByPublicKey(encodedData1, publicKey);
                String target1 = new String(decodedData1);
                System.out.println("解密后: \r\n" + target1);
                System.err.println("私钥签名——公钥验证签名");
                String sign = sign(encodedData, privateKey);
                System.err.println("签名:\r" + sign);
                boolean status = verify(encodedData, publicKey, sign);
                System.err.println("验证结果:\r" + status);
//			//对内容进行加密和解密
                String str = "this is test RSA";
                byte[] strByte = str.getBytes();
//			//私钥加密
                byte[] encode = encryptByPrivateKey(strByte,privateKey);
                Logger.info("私钥加密结果:" + encryptBASE64(encode));
                //公钥解密
                byte[] decode = decryptByPublicKey(encode,publicKey);
                Logger.info("公钥解密结果:" + new String(decode));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }



}
