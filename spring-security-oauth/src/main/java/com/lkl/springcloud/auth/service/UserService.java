package com.lkl.springcloud.auth.service;

import com.lkl.springcloud.auth.entity.RcUserEntity;

public interface UserService {
	RcUserEntity findByUsername(String username);
}
