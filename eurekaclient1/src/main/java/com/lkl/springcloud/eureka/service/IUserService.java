package com.lkl.springcloud.eureka.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value="clientService2",fallbackFactory=UserServiceFallbackFactoryImpl.class)
public interface IUserService {
 
    @RequestMapping(value = "/discovery", method = RequestMethod.GET)
    String findService();
 
}
