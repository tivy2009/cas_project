package com.lkl.springcloud.eureka.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "userService")
public interface IUserService {
 
    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    String findService();
 
}
