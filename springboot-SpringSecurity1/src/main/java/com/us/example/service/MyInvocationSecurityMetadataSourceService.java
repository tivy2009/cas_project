package com.us.example.service;

import com.us.example.dao.PermissionDao;
import com.us.example.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by yangyibo on 17/1/19.
 */
@Service
public class MyInvocationSecurityMetadataSourceService  implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionDao permissionDao;

    private HashMap<String, Collection<ConfigAttribute>> map =null;

    /**
     * 加载资源，初始化资源变量
     */
    public void loadResourceDefine(){
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<Permission> permissions = permissionDao.findAll();
        for(Permission permission : permissions) {
            cfg = new SecurityConfig(permission.getName());
            if(map.get(permission.getUrl()) == null)
            {
                array = new ArrayList<>();
                array.add(cfg);
                map.put(permission.getUrl(), array);
            }else {
                map.get(permission.getUrl()).add(cfg);
            }
            
        }

    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //生成应该从缓存读取资源
        //if(map ==null) loadResourceDefine();
        loadResourceDefine();
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(String key : map.keySet()) {
            resUrl = key;
        //for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            //resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
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
