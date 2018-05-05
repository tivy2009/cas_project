package com.phy.common.security.service;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD:springboot-SpringSecurity1/src/main/java/com/us/example/service/CustomUserService.java
import com.us.example.dao.PermissionDao;
import com.us.example.dao.UserDao;
import com.us.example.domain.Permission;
import com.us.example.domain.SysUser;

=======
>>>>>>> a18485c54f5c6c6437f4b658621f637ab09ccf4f:commonSecurity/src/main/java/com/phy/common/security/service/CustomUserDetailsService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
<<<<<<< HEAD:springboot-SpringSecurity1/src/main/java/com/us/example/service/CustomUserService.java
                if (permission != null && permission.getName()!=null) {
                    GrantedAuthority grantedAuthority = new UrlGrantedAuthority(permission.getUrl(),permission.getMethod());
=======
                if (permission != null && permission.getName() != null) {

                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
>>>>>>> a18485c54f5c6c6437f4b658621f637ab09ccf4f:commonSecurity/src/main/java/com/phy/common/security/service/CustomUserDetailsService.java
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
