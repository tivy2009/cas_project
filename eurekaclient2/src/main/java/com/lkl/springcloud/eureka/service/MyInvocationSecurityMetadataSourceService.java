package com.lkl.springcloud.eureka.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

/**
 * 
 * @desc: 通过getAttributes方法返回调用请求的方法所必须要有的角色
 * @author: tivy
 * @createTime: 2018-05-03 10:28:08
 * @history:
 * @version: v1.0
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {


    private HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 加载资源，初始化资源变量
     * 
     * @author: tivy
     * @createTime: 2018-05-03 10:28:18
     * @history: void
     */
    public void loadResourceDefine() {
        map = new HashMap<>();

    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 生成应该从缓存读取资源
        // if(map ==null) loadResourceDefine();
        loadResourceDefine();
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        StringBuffer url = request.getRequestURL();
        System.out.println("url:"+url);
        AntPathRequestMatcher matcher;
        for (String key : map.keySet()) {
            System.out.println("key:"+key);
            matcher = new AntPathRequestMatcher(key);
            if (matcher.matches(request)) {
                return map.get(key);
            }
        }
        Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>(); 
        returnCollection.add(new SecurityConfig("ROLE_NO_USER"));
        return returnCollection;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
