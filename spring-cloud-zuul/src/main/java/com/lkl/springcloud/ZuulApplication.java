package com.lkl.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.lkl.springcloud.filter.AccessUserNameFilter;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
    
    @Bean
    public AccessUserNameFilter accessUserNameFilter() {  
        return new AccessUserNameFilter();  
    }  
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
    }
}