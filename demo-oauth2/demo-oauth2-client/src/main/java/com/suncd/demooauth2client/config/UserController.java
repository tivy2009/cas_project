/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.suncd.demooauth2client.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2018/3/2 14:59
 */
@RestController
public class UserController {

    @GetMapping("/get")
    public String get(){
        return "get gxyc";
    }


    @GetMapping("/getRemote")
    public String getR(){
        String ls = "";//restTemplate.getForObject("http://localhost:8081/auth/user/me1",String.class);
        return ls;
    }
}
