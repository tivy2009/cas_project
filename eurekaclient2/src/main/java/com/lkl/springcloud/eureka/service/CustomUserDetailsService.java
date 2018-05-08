package com.lkl.springcloud.eureka.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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

    public UserDetails loadUserByUsername(String username) {
            
            UserDetails user = new UserDetails() {
				private static final long serialVersionUID = 1L;

				@Override
				public Collection<? extends GrantedAuthority> getAuthorities() {
					// TODO Auto-generated method stub
					List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			    	
		            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_HOME");
		            grantedAuthorities.add(grantedAuthority);
					return grantedAuthorities;
				}

				@Override
				public String getPassword() {
					// TODO Auto-generated method stub
					return "BBBBBBBBBBB";
				}

				@Override
				public String getUsername() {
					// TODO Auto-generated method stub
					return "AAAAAAA";
				}

				@Override
				public boolean isAccountNonExpired() {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				public boolean isAccountNonLocked() {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				public boolean isCredentialsNonExpired() {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				public boolean isEnabled() {
					// TODO Auto-generated method stub
					return true;
				}
            	
            };
            return user;
        
    }

}
