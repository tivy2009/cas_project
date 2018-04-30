package com.lkl.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lkl.springcloud.filter.AccessUserNameFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@RestController
public class ZuulApplication {
    
	@RequestMapping("/")
    public String home() {
        return "hello spring-cloud-zuul!";
    }
	
    @Bean
    public AccessUserNameFilter accessUserNameFilter() {  
        return new AccessUserNameFilter();  
    }  
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }
}