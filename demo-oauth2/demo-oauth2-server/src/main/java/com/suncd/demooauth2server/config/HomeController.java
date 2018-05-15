/*
 * Copyright @ 2018 tivy
 * demo-oauth2-server 2018-05-15 09:08:26
 * All right reserved.
 *
 */
package com.suncd.demooauth2server.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @desc: demo-oauth2-server
 * @author: tivy
 * @createTime: 2018-05-15 09:08:26
 * @history:
 * @version: v1.0
 */
@Controller
public class HomeController {

    /**
     * 首页
     * @author: tivy
     * @createTime: 2018-05-15 09:00:30
     * @history:
     * @return String
     */
    @RequestMapping(value= {"","/"})
    public String index() {
        return "index";
    }
    
}
