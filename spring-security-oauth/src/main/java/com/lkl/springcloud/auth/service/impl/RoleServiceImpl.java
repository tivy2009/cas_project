package com.lkl.springcloud.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lkl.springcloud.auth.entity.RcRoleEntity;
import com.lkl.springcloud.auth.repository.RoleRepository;
import com.lkl.springcloud.auth.service.RoleService;

/**
 * Created by Mr.Yangxiufeng on 2017/12/29.
 * Time:12:31
 * ProjectName:Mirco-Service-Skeleton
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<RcRoleEntity> getRoleValuesByUserId(Integer userId) {
        return roleRepository.getRoleValuesByUserId(userId);
    }
}