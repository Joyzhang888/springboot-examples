package com.hong.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by hong on 2017/5/6.
 * 密码md5加密
 */
public class PwdUtil {

    /**
     * SimpleHash
     * @param password　密码　
     * @param salt　盐值
     * @return
     */
    public static SimpleHash createMD5Credentials(String password,String salt){
        String hashAlgorithmName ="MD5";
        Object credentials =password;
        int hashIterations=1024;

        /**
         *  hashAlgorithmName 算法名称     MD5,SHA1,GOST94,GOST89MAC,SHA256,SHA384,AEAD;
         *  credentials 明文密码
         *  salt 盐值
         *  hashIterations 加密次数
         */
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
    }
}
