package com.phy.common.security.dao;

import java.util.List;

import com.phy.common.security.entity.Permission;

/**
 * 
 * @desc: 权限接口类
 * @author: tivy
 * @createTime: 2018-05-03 10:32:00
 * @history:
 * @version: v1.0
 */
public interface PermissionDao {
    public List<Permission> findAll();

    public List<Permission> findByAdminUserId(int userId);
}
