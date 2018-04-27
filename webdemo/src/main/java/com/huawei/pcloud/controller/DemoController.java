package com.huawei.pcloud.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.huawei.pcloud.bean.User;
import com.huawei.pcloud.utils.HostPropertiesUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/")
public class DemoController {

	private static final Logger logger = LogManager.getLogger();
	
	@RequestMapping("/demo")
	public String index() {
		return "demo";
	}

	@RequestMapping("/")
	public ModelAndView systemProperty() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("name", "spring");

		InetAddress netAddress = HostPropertiesUtil.getInetAddress();
		String ip = HostPropertiesUtil.getHostIp(netAddress);
		String hostname = HostPropertiesUtil.getHostName(netAddress);
		Properties properties = System.getProperties();
		Set<String> set = properties.stringPropertyNames();
		Map<String, Object> systemProperties = new HashMap<String,Object>();
		for (String name : set) {
			systemProperties.put(name, properties.getProperty(name));
		}
		modelAndView.addObject("ip", ip);
		modelAndView.addObject("hostname", hostname);
		modelAndView.addObject("systemProperties", systemProperties);
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value = "/json", method = { RequestMethod.POST, RequestMethod.GET })
	public void json(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//http://localhost:8080/webdemo/json?username=aaaa
		/* 通过responsse返回数据 */
		String username = request.getParameter("username");
		logger.info("username:" + username);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write("get json demo sucess params-->username:" + username);
	}

	@RequestMapping(value = "/mode")
	public ModelAndView userinfo(User user) {
		//http://localhost:8080/webdemo/mode?username=aaaa&password=bbbbb
		/* 通过ModelAndView来返回数据 */
		logger.info("username:" + user.getUsername());
		ModelAndView mav = new ModelAndView("user");
		mav.addObject("username", user.getUsername());
		mav.addObject("password", user.getPassword());
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search",method = { RequestMethod.POST})
	@ResponseBody
	public String search(@RequestBody Map<String, Object> map) {
		logger.info("传进来的参数：" + map);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		logger.info("json：" + json);
		Map<String,Object> mapObj = gson.fromJson(json, Map.class);
		logger.info("转换后的参数：" + mapObj);
		return json;
	}
}