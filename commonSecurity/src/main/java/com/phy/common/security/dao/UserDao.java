package com.phy.common.security.dao;

import com.phy.common.security.entity.SysUser;

/**
 * 
 * @desc: 用户接口类
 * @author: tivy
 * @createTime: 2018-05-03 10:32:06
 * @history:
 * @version: v1.0
 */
public interface UserDao {
    public SysUser findByUserName(String username);
}
