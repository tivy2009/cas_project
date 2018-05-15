/*
 * Copyright @ 2018 tivy
 * demo-webapp-client3 2018-05-15 10:22:51
 * All right reserved.
 *
 */
package com.suncd.demooauth2client.servlet;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.suncd.demooauth2client.service.SsoLoginService;

/**
 * @desc: demo-webapp-client3
 * @author: tivy
 * @createTime: 2018-05-15 10:22:51
 * @history:
 * @version: v1.0
 */
public class SSOLoginServlet extends HttpServlet{

    private static final long serialVersionUID = 8079597991640448584L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO Auto-generated method stub
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        String url = httpRequest.getRequestURL().toString();
        HttpSession session = httpRequest.getSession();
        
        //判断用户是否已经登录
        
        Object user = session.getAttribute("user");
        if(user ==null )
        {
            String code = httpRequest.getParameter("code");
            
            SsoLoginService ssoLoginService = new SsoLoginService();
            
            if(code !=null && code.length() >0)
            {
                String accessToken = ssoLoginService.getAccessToken(code);
                if(accessToken !=null && accessToken.length() >0)
                {
                    Map principal = ssoLoginService.getUser(accessToken);
                    session.setAttribute("user",principal);
                    //重定向到最初用户的访问界面
                    String original_url = (String)session.getAttribute("original_url");
                    if(original_url == null) original_url = SsoLoginService.redirect_uri;
                    httpResponse.sendRedirect(original_url);
                    return;
                }
            }
            
            //没有登录,重定向到登录地址
            httpResponse.sendRedirect(SsoLoginService.redirect_uri);
            return;
        }
        //已经登录,重定向到最初用户的访问界面
        String original_url = (String)session.getAttribute("original_url");
        if(original_url == null) original_url = SsoLoginService.redirect_uri;
        httpResponse.sendRedirect(original_url);
        return;
    }

}
