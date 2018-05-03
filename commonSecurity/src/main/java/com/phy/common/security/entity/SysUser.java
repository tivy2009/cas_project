package com.phy.common.security.entity;

import java.util.List;

/**
 * 
 * @desc: springboot-security
 * @author: tivy
 * @createTime: 2018-05-03 10:32:46
 * @history:
 * @version: v1.0
 */
public class SysUser {
    private String id;
    private String username;
    private String password;

    private List<SysRole> roles;

    public String getId() {
    
        return id;
    }

    public void setId(String id) {
    
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

}
