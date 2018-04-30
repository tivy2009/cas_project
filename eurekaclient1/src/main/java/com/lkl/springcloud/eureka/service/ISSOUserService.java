package com.lkl.springcloud.eureka.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "oauthservice", fallbackFactory = SSOUserServiceFallbackFactoryImpl.class)
public interface ISSOUserService {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	String getUser(@RequestParam(value = "access_token") String access_token);

}
