package com.lkl.springcloud.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableDiscoveryClient
@EnableFeignClients
@EnableAuthorizationServer
public class App {
	
    @RequestMapping("/")
    public String home() {
        return "Hello world eurekaclient2";
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).web(true).run(args);
    }
}
