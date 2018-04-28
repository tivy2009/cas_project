package com.lkl.springcloud.eureka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * implements the fallbackFactory function of the hystrix by IUserService 
 * @author tivy
 *
 */
@Component
public class UserServiceFallbackFactoryImpl implements FallbackFactory<IUserService> {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceFallbackFactoryImpl.class);
    
    @Override
    public IUserService create(Throwable cause) {
        logger.info("fallback reason was: {} " ,cause.getMessage());  
        return new IUserService() {
            @Override
            public String findService() {
                return "FallbackFactory";
            }
        };
    }

}
