package com.phy.common.security.entity;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @desc: springboot-security
 * @author: tivy
 * @createTime: 2018-05-03 10:32:46
 * @history:
 * @version: v1.0
 */
<<<<<<< HEAD:springboot-SpringSecurity1/src/main/java/com/us/example/domain/SysUser.java

public class SysUser implements UserDetails {
	private static final long serialVersionUID = -2005115449938091620L;
	private Integer id;
=======
public class SysUser {
    private String id;
>>>>>>> a18485c54f5c6c6437f4b658621f637ab09ccf4f:commonSecurity/src/main/java/com/phy/common/security/entity/SysUser.java
    private String username;
    private String password;

    private List<SysRole> roles;
    
    private List<? extends GrantedAuthority> authorities;

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

	public List<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
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

}
