package com.lkl.springcloud.security;

import java.security.Principal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@RestController
@EnableDiscoveryClient
@EnableFeignClients
public class Application {

    @RequestMapping("/")
    public String home() {
        return "Hello world spring-cloud-security";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }

}
