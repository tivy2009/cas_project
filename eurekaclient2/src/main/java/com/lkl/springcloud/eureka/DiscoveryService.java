package com.lkl.springcloud.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lkl.springcloud.eureka.sso.SsoLoginService;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liaokailin on 16/4/30.
 */
@Component
@RestController
public class DiscoveryService {

    @Autowired
    private DiscoveryClient discoveryClient;
    
    @RequestMapping("/discovery")
    public String doDiscoveryService(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        
        System.out.println("username:"+userDetails.getUsername());
        
        StringBuilder buf = new StringBuilder();
        List<String> serviceIds = discoveryClient.getServices();
        if(!CollectionUtils.isEmpty(serviceIds)){
            for(String s : serviceIds){
                System.out.println("serviceId:" + s);
                List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
                if(!CollectionUtils.isEmpty(serviceInstances)){
                    for(ServiceInstance si:serviceInstances){
                        buf.append("["+si.getServiceId() +" host=" +si.getHost()+" port="+si.getPort()+" uri="+si.getUri()+"]");
                    }
                }else{
                    buf.append("no service.");
                }
            }
        }
        return buf.toString();
    }
    
    @RequestMapping("/code")
    public String code(String code) throws UnsupportedEncodingException {
    	System.out.println("code:"+code);
    	SsoLoginService ssoLoginService = new SsoLoginService();
    	String accessToken = ssoLoginService.getAccessToken(code);
    	
    	UserDetails userDetails = new org.springframework.security.core.userdetails.User(
    	        "AAAAAAA", 
    	        "BBBBBBB", 
    	        new ArrayList<GrantedAuthority>());

        SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());
        UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
                userDetails, accessToken, userDetails.getAuthorities());
        newAuthentication.setDetails(userDetails);
    	SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    	
        return "accessToken:"+accessToken;
    }
    
}