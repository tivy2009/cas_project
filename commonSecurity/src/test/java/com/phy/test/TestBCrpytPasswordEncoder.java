/*
 * Copyright @ 2018 tivy
 * commonSecurity 2018-05-07 16:09:46
 * All right reserved.
 *
 */
package com.phy.test;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @desc: commonSecurity
 * @author: tivy
 * @createTime: 2018-05-07 16:09:46
 * @history:
 * @version: v1.0
 */
public class TestBCrpytPasswordEncoder {

    /**
     * @author: tivy
     * @createTime: 2018-05-07 16:09:46
     * @history:
     * @param args void
     */
    public static void main(String[] args) {
        //TODO Auto-generated method stub
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
        
        String rawPassword = "123456";
        String encodedPassword = "$2a$10$QW1hk5B8lmB/65.7yHWpH.EDgTpnlKjLJCOs8REx3potQ3Ubp7qqy";
        boolean flag = BCrypt.checkpw(rawPassword.toString(), encodedPassword);
        System.out.println(flag);
    }

}
