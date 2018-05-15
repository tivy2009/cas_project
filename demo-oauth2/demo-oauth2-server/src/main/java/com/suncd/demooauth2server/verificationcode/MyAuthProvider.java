package com.suncd.demooauth2server.verificationcode;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 可以实现验证码功能
 * @author tivy
 *
 */
@Component
public class MyAuthProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//验证码等校验
        UserDetails details = (UserDetails) authentication.getDetails();
        
//        System.out.println(details.getToken() + "+++++++++++++++++" + details.getSessionToken());
//        if (!details.getToken().equalsIgnoreCase(details.getSessionToken())) {
//            throw new BadCredentialsException("验证码错误。");
//        }

        //用户名密码校验
//        OauthUserDetails oauthUserDetails = (OauthUserDetails) oauthUserDetailsService
//                .loadUserByUsername(authentication.getName());
//        System.out.println(authentication.getName() + "+++++++++++++++++" + authentication.getCredentials());
//        if (!oauthUserDetails.getUserName().equals(authentication.getName())
//                || !oauthUserDetails.getPassword().equals(authentication.getCredentials())) {
//            throw new BadCredentialsException("用户名或密码错误。");
//        }
//        Collection<? extends GrantedAuthority> authorities = oauthUserDetails.getAuthorities();
//        return new UsernamePasswordAuthenticationToken(oauthUserDetails.getUsername(), oauthUserDetails.getPassword(),
//                authorities);
        return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
