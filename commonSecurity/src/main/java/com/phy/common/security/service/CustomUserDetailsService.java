package com.phy.common.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phy.common.security.dao.PermissionDao;
import com.phy.common.security.dao.UserDao;
import com.phy.common.security.entity.Permission;
import com.phy.common.security.entity.SysUser;

/**
 * 
 * @desc: 自定义UserDetailsService 接口
 * @author: tivy
 * @createTime: 2018-05-03 10:32:56
 * @history:
 * @version: v1.0
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Autowired
    PermissionDao permissionDao;

    public UserDetails loadUserByUsername(String username) {
//        SysUser user = userDao.findByUserName(username);
//        if (user != null) {
//            List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
//            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
//            for (Permission permission : permissions) {
//                if (permission != null && permission.getName()!=null) {
//
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
//                grantedAuthorities.add(grantedAuthority);
//                }
//            }
//            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
//        } else {
//            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
//        }
        
        
        SysUser user = userDao.findByUserName(username);
        if (user != null) {
            List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            user.setAuthorities(grantedAuthorities);
            return user;
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
        
    }

}
