package com.lkl.springcloud.auth.service;

import java.util.List;

import com.lkl.springcloud.auth.entity.RcMenuEntity;

public interface PermissionService {
	List<RcMenuEntity> getPermissionsByRoleId(Integer roleId);
}
