package com.lkl.springcloud.eureka.service;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class UserServiceByFeignImpl implements FallbackFactory<IUserService> {

    @Override
    public IUserService create(Throwable cause) {
        return new IUserService() {
            @Override
            public String findService() {
                return "FallbackFactory";
            }
        };
    }

}
