package cn.demo.encrypt;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;

public class BASE64EncoderDemo {

    public static void main(String[] args) throws Exception{
        System.out.println(new BASE64Encoder().encodeBuffer("http".getBytes("utf-8")));
        System.out.println(new String(new BASE64Decoder().decodeBuffer("aHR0cA=="),"utf-8"));

        MessageDigest md5 = MessageDigest.getInstance("md5");//sha
       Provider[] providers = Security.getProviders();
        for (Provider provider : providers ) {
            System.out.println(provider);
        }
        md5.update("123".getBytes());
        System.out.println(md5.digest());

        //HMAC(Hash Message Authentication Code，散列消息鉴别码，基于密钥的Hash算法的认证协议

    }
}
