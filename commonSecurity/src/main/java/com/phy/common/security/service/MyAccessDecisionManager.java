package com.phy.common.security.service;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

/**
 * 
 * @desc: 验证用户是否有请求权限
 * @author: tivy
 * @createTime: 2018-05-03 10:23:07
 * @history:
 * @version: v1.0
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        if ("anonymousUser".equals(authentication.getPrincipal()) || matchers("/images/**", request)
                || matchers("/js/**", request) || matchers("/css/**", request) || matchers("/fonts/**", request)
                || matchers("/index.html", request) || matchers("/favicon.ico", request)
                || matchers("/login", request)) {
            return;
        } else {
            if (null == configAttributes || configAttributes.size() <= 0) {
                return;
            }
            ConfigAttribute c;
            String needRole;
            for (Iterator<ConfigAttribute> iter = configAttributes.iterator(); iter.hasNext();) {
                c = iter.next();
                needRole = c.getAttribute();
                for (GrantedAuthority ga : authentication.getAuthorities()) {
                    if (needRole.trim().equals(ga.getAuthority())) {
                        return;
                    }
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    /**
     * 
     * @author: tivy
     * @createTime: 2018-05-03 10:23:33
     * @history:
     * @param url
     * @param request
     * @return boolean
     */
    private boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
