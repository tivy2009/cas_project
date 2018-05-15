/*
 * Copyright @ 2018 tivy
 * demo-webapp-client3 2018-05-15 09:53:52
 * All right reserved.
 *
 */
package com.suncd.demooauth2client.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.Filter;  
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.suncd.demooauth2client.service.SsoLoginService;

/**
 * @desc: demo-webapp-client3
 * @author: tivy
 * @createTime: 2018-05-15 09:53:52
 * @history:
 * @version: v1.0
 */
public class SSOFilter implements Filter {
    
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
        
        System.out.println("uri:"+uri + " url:"+url);
        String code = httpRequest.getParameter("code");
        Object user = session.getAttribute("user");
        
        Collection<String> headerNames = httpResponse.getHeaderNames();
        Iterator<String> itre = headerNames.iterator();
        while(itre.hasNext())
        {
            String key = itre.next();
            String value = httpResponse.getHeader(key);
            System.out.println("header key:"+key +" value:"+value);
        }
        
        if(user ==null && (code == null || code.length() == 0) && !uri.equals(contextPath+"/login"))
        {
            //保存用户的原始登录地址
            session.setAttribute("original_url", url);
            //没有登录,重定向到本地登录地址
            httpResponse.sendRedirect(SsoLoginService.redirect_uri);
            return;
        }else if(user ==null && (code == null || code.length() == 0) && uri.equals(contextPath+"/login"))
        {
            //没有登录,重定向到远程登录地址
            httpResponse.sendRedirect(SsoLoginService.sso_login_url);
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

    /*
     *(non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        //TODO Auto-generated method stub
        
    }

}
