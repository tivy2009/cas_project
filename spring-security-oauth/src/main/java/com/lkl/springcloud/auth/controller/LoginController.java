package com.lkl.springcloud.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	String welcome() {
		return "welcome";
	}

//	@RequestMapping("/")
//	public String index() {
//		return "index";
//	}
//
//	@RequestMapping("/login")
//	public String login() {
//		return "login";
//	}

	// @RequestMapping(value = "/login",method = RequestMethod.GET)
	// public String doLogin(String username,String password){
	// System.out.println(username);
	// return "index";
	// }
}
