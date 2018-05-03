package com.phy.common.security;

import static org.springframework.boot.SpringApplication.run;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @desc: springboot-security
 * @author: tivy
 * @createTime: 2018-05-03 10:30:42
 * @history:
 * @version: v1.0
 */
@ComponentScan(basePackages = "com.phy.common.security")
@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = run(SecurityApplication.class, args);
    }

}
