package com.suncd.demooauth2server.verificationcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 增加自定义验证过滤器，可用于实现验证码功能
 * @author tivy
 *
 */
public class MyAuthFilter extends AbstractAuthenticationProcessingFilter  {
	
	public MyAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		Enumeration e = request.getParameterNames();
		while(e.hasMoreElements())
		{
			String key = (String)e.nextElement();
			String value = request.getParameter(key);
			System.out.println("key:"+key+" value:"+value);
		}
		Collection<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		return new UsernamePasswordAuthenticationToken("john", "123",authorities);
	}

}
