/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.suncd.demooauth2client.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * 功能：XXXX
 *
 * @author qust
 * @version 1.0 2018/3/5 10:44
 */

@Controller
public class PublicController {

    @GetMapping("securedPage")
    public String getPage(Principal principal,ModelMap modelMap,String code,String status){
        modelMap.put("c","c");
        return "securedPage";
    }

    @GetMapping("login1")
    public String login(Principal principal,ModelMap modelMap,String code,String status){
        modelMap.put("c","c");
        return "securedPage";
    }
}
