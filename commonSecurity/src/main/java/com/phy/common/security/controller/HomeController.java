package com.phy.common.security.controller;

import java.security.Principal;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
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
    	
    	ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
    	HttpServletRequest  request = attrs.getRequest();
    	HttpSession session = request.getSession();
    	
    	Enumeration<String> enums = session.getAttributeNames();
    	
    	System.out.println("-----------------------------------");
    	while(enums.hasMoreElements())
    	{
    		String key = enums.nextElement();
    		Object value = session.getAttribute(key);
    		System.out.println("value:"+value.toString());
    		System.out.println(value.getClass().getName());
    		System.out.println("key:"+key+" value:"+JSON.toJSONString(value));
    	}
    	System.out.println("-----------------------------------");
    	String jsonString = JSON.toJSONString(session);
    	System.out.println("jsonString:"+jsonString);
        return jsonString;
    }
    
    @RequestMapping("/user/info")
    @ResponseBody
    public Principal user(Principal user) {
        System.out.println(user.getClass().getName());
        System.out.println(user.getName());
        return user;
    }
}
