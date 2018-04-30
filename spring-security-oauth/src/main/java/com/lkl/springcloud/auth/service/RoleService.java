package com.lkl.springcloud.auth.service;

import java.util.List;

import com.lkl.springcloud.auth.entity.RcRoleEntity;

public interface RoleService {
	List<RcRoleEntity> getRoleValuesByUserId(Integer userId);
}
