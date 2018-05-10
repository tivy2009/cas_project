package com.suncd.demooauth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class DemoOauth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoOauth2ServerApplication.class, args);
	}
}
