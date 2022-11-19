package com.jink.jinblog.util;

import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/19 16:36:31
 */
public class MD5Utils {

    public static Random random = new SecureRandom();

    /**
     * 加盐MD5算法
     * @param password
     * @return
     */
    public static String getSaltMD5(String password) throws Exception{
        StringBuilder sb = new StringBuilder(16);
        sb.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = sb.length();
        if(len < 18){
            int diffLen = 16 - len;
            for(int i = 0; i < diffLen; i ++){
                sb.append(0);
            }
        }
        String salt = sb.toString();
        password = md5Hex(password + salt);
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return String.valueOf(cs);
    }

    /**
     * 使用Apache的Hex类实现Hex(16进制字符串和)和字节数组的互转
     * @param str 原明文字符串
     * @return 转换后的字符
     */
    private static String md5Hex(String str) throws Exception{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(str.getBytes());
        return new String(Hex.encode(digest));
    }

    /**
     * 验证加盐后是否和原文一致
     * @param password 原始密码
     * @param md5str md5后值
     * @return
     */
    public static boolean getSaltVerifyMD5(String password, String md5str) throws Exception{
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5str.charAt(i);
            cs1[i / 3 * 2 + 1] = md5str.charAt(i + 2);
            cs2[i / 3] = md5str.charAt(i + 1);
        }
        String salt = new String(cs2);
        return md5Hex(password + salt).equals(String.valueOf(cs1));
    }
}
