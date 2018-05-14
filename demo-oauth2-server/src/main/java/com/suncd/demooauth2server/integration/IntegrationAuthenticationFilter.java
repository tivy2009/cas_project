package com.suncd.demooauth2server.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class IntegrationAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware  {
	private static final String AUTH_TYPE_PARM_NAME = "auth_type";
	 
	  private static final String OAUTH_TOKEN_URL = "/oauth/token";
	 
	  private ApplicationContext applicationContext;
	 
	  private RequestMatcher requestMatcher;
	 
	  public IntegrationAuthenticationFilter(){
	    this.requestMatcher = new OrRequestMatcher(
	        new AntPathRequestMatcher(OAUTH_TOKEN_URL, "GET"),
	        new AntPathRequestMatcher(OAUTH_TOKEN_URL, "POST"),
	        new AntPathRequestMatcher("/oauth/authorize", "GET"),
	        new AntPathRequestMatcher("/oauth/authorize", "POST")
	    );
	  }
	 
	  @Override
	  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	 
	    HttpServletRequest request = (HttpServletRequest) servletRequest;
	    HttpServletResponse response = (HttpServletResponse) servletResponse;
	 
	    if(requestMatcher.matches(request)){
	    	
	    	Enumeration enumer = 	request.getParameterNames();
	    	while(enumer.hasMoreElements())
	    	{
	    		String key = (String)enumer.nextElement();
	    		String value = request.getParameter(key);
	    		System.out.println("key:"+key+" value:"+value);
	    		
	    	}
	    	
	      try{
	        filterChain.doFilter(request,response);
	      }finally {
	    	  
	      }
	    }else{
	      filterChain.doFilter(request,response);
	    }
	  }
	 
	  @Override
	  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	    this.applicationContext = applicationContext;
	  }
}
