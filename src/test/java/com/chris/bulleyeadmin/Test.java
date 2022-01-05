package com.chris.bulleyeadmin;

import com.chris.bulleyeadmin.common.utils.Base64Utils;
import com.chris.bulleyeadmin.common.utils.RSAUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Auther: Chris
 * @Date: 2019-02-14 17:20
 * @Description:
 */
public class Test {


    public static void main(String[] args) throws Exception {
        String str = "zhx";
        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+kQaJu6GdGs2DsH7W13IVPY+Sr/P5knpoIHxEZcT4WS80dGOMess8ks22C2P5ixhluOskREvy/PfZWrgTZ3uoa4jiTSLW3kTg50qgDmIU6jY6nptWbKaQp/Nxjcf2r44kR+MqAlpnzPyO0ED086M5iDMa7lEGwx41gMCFrJzhDQIDAQAB";
        byte[] b =  RSAUtils.encryptByPublicKey(str.getBytes(StandardCharsets.UTF_8),pub);
        String pri  = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL6RBom7oZ0azYOwftbXchU9j5Kv8/mSemggfERlxPhZLzR0Y4x6yzySzbYLY/mLGGW46yRES/L899lauBNne6hriOJNItbeRODnSqAOYhTqNjqem1ZsppCn83GNx/avjiRH4yoCWmfM/I7QQPTzozmIMxruUQbDHjWAwIWsnOENAgMBAAECgYA67cnhyZ3eYS1wLaHredATdWfyamq9oGgtzu3ekMHqMloAlIU8KbSzVIo8YyVVQ4gV3KsKGfyKdWOHWSBXD8cS8xmgmBJXC8SJOUmNncEv+q/MUG1BFqkQRgywvfGRC7IwKQ5qNQHlTOJ9rQzz77SgKIFKupWp0OBbE0/wmbcrgQJBAOIteGbrzdOsfklgw3rW0FDqBg/TZnQQ3Jrc560QYkpXYG12WX424Tpr8NknfnlgdebJfQEwlH3auVMOhHi/RFECQQDXsYRnrl5H3Z8M8F5KWBYj0qxBdBErQy3pGZXlPEdmaTgTveVdwvADuZaRlLAlnozLGudruDoz9KMDcOz+6k39AkEAhO5gk20H3d7Fom7itTHH4aaJxYdj8ddvMP2glJDKlBLis7MZShsLKQED3z1dEg1lfizx8V8SBx4kSfKAXU9c4QJBAJ7PEBq6kqY9Mcb4D6STWLHFgpiqmZ2N1/bBpNDkYfmwTN6UOZWgWtGTSp4jqNH5+9XrGCsCuK/aVk/JTGWgJA0CQHcU5WfKqh9HzM0VA2RpGbWjtnc+X9cZ6LzrrjwBzXta+MtBVTh32yiR36xDltoEhm2TXizxSrNIHYvDz3VDZTc=";
        System.out.println("加密："+b.toString());
        String st = "W/vNnncVJWfwFqnqY9SupF5or3GRCX0wOI8PxVBxvqgsfZH09xv2suB86uaW/D2PWnMW/E0KWE2Bpws5p4xah+VS6eo9GoHykJD56k0rpXrfUIe9INKsAW9Yxd5Wbe514Uhpu8oZjbWBB+cJRUmQC8KB55VDn2Ogy6d6PTZsIjM=";
        byte[] b1 =  RSAUtils.decryptByPrivateKey(Base64Utils.decode(st),pri);
        System.out.println("解密："+new String(b1));

    }
}
