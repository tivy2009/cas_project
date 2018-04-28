package com.lkl.springcloud.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lkl.springcloud.eureka.service.IUserService;

/**
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableDiscoveryClient
@EnableFeignClients
public class App {
    
    @Autowired
    private IUserService userService;
    
    @RequestMapping("/")
    public String home() {
        return "Hello world eurekaclient1";
    }
    
    @RequestMapping("/findService")
    public String findService(){
        return userService.findService();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).web(true).run(args);
    }
}
