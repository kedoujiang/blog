package com.jink.jinblog.test;

import com.jink.jinblog.security.PBKDF2PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/26 18:43:06
 */
public class UnitTest {

    @Autowired
    private PBKDF2PasswordEncoder passwordEncoder;

    @Test
    public void test() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                .generateSecret(new PBEKeySpec("123456".toString().toCharArray(), "jink6688".getBytes(), 33, 256))
                .getEncoded();
        String s = Base64.getEncoder().encodeToString(result);
        System.out.println(s);
    }
}
