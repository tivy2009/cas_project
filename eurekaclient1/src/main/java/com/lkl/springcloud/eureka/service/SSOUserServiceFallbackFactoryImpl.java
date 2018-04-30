package com.lkl.springcloud.eureka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class SSOUserServiceFallbackFactoryImpl implements FallbackFactory<ISSOUserService> {

	private static final Logger logger = LoggerFactory.getLogger(SSOUserServiceFallbackFactoryImpl.class);

	@Override
	public ISSOUserService create(Throwable cause) {
		logger.info("fallback reason was: {} ", cause.getMessage());
		return new ISSOUserService() {
			@Override
			public String getUser(String accsss_token) {
				// TODO Auto-generated method stub
				return "FallbackFactory";
			}

		};
	}

}
