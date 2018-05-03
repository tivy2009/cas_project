package com.phy.common.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phy.common.security.entity.Msg;

/**
 * 
 * @desc: HOME主页Controller类
 * @author: tivy
 * @createTime: 2018-05-03 10:31:54
 * @history:
 * @version: v1.0
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(Model model) {
        Msg msg = new Msg("标题", "内容", "wellcome to the home page!");
        model.addAttribute("msg", msg);
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String hello() {
        return "hello admin";
    }
}
