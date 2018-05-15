/*
 * Copyright @ 2018 tivy
 * demo-webapp-client3 2018-05-15 09:53:52
 * All right reserved.
 *
 */
package com.suncd.demooauth2client.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;  
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

/**
 * @desc: demo-webapp-client3
 * @author: tivy
 * @createTime: 2018-05-15 09:53:52
 * @history:
 * @version: v1.0
 */
public class SSOFilter implements Filter {
    
	private String[] excludedPages;
	
	private String ssoconfig;

    /*
     *(non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @SuppressWarnings("unchecked")
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //TODO Auto-generated method stub
    	String excludedPage = filterConfig.getInitParameter("excludedPages");
        if (excludedPage != null && excludedPage.length() > 0){
            excludedPages = excludedPage.split(",");
        }
        ssoconfig = filterConfig.getInitParameter("ssoconfig");
        Map<String,Object> ssoConfigMap = JSON.parseObject(ssoconfig, Map.class);
        if(ssoConfigMap.get("sso_url") != null)
        {
        	SSOConstant.SSO_URL = (String)ssoConfigMap.get("sso_url");
        }
        if(ssoConfigMap.get("client_id") != null)
        {
        	SSOConstant.CLIENT_ID = (String)ssoConfigMap.get("client_id");
        }
        if(ssoConfigMap.get("client_secret") != null)
        {
        	SSOConstant.CLIENT_SECRET = (String)ssoConfigMap.get("client_secret");
        }
        if(ssoConfigMap.get("sub_app_url") != null)
        {
        	SSOConstant.SUB_APP_URL = (String)ssoConfigMap.get("sub_app_url");
        }
    }
	
    /*
     *(non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //判断用户是否已经登录
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpSession session = httpRequest.getSession();
        String contextPath = httpRequest.getContextPath();
        String url = httpRequest.getRequestURL().toString();
        String uri = httpRequest.getRequestURI();
        
        if(excludedPages !=null && excludedPages.length >0)
        {
        	for(String regEx : excludedPages)
        	{
        		Pattern pattern = Pattern.compile(regEx);
        		Matcher matcher = pattern.matcher(uri);
        		if(matcher.matches())
        		{
        			chain.doFilter(request, response);
                    return;
        		}
        	}
        }
        
        String code = httpRequest.getParameter("code");
        Object user = session.getAttribute("user");
        
        if(user ==null && (code == null || code.length() == 0) && !uri.equals(contextPath+"/login"))
        {
            //保存用户的原始登录地址
            Cookie cookie = new Cookie("original_url",url);
            httpResponse.addCookie(cookie);
            //没有登录,重定向到本地登录地址
            httpResponse.sendRedirect(SSOConstant.REDIRECT_URI);
            return;
        }else if(user ==null && (code == null || code.length() == 0) && uri.equals(contextPath+"/login"))
        {
            //没有登录,重定向到远程登录地址
            httpResponse.sendRedirect(SSOConstant.SSO_LOGIN_URL);
            return;
        }else if(user == null && code != null && code.length() >0 && uri.equals(contextPath+"/login"))
        {
            chain.doFilter(request, response);
            return;
        }
        else {
            //已经登录
            chain.doFilter(request, response);
            return;
        }
    }

    /*
     *(non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        //TODO Auto-generated method stub
        
    }

}
