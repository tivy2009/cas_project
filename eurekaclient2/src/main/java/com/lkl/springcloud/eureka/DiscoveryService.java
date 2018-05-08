package com.lkl.springcloud.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lkl.springcloud.eureka.sso.SsoLoginService;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
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
    
	@Autowired
    private ApplicationContext applicationContext;
    
    @RequestMapping("/discovery")
    public String doDiscoveryService(){
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
    	Principal principal = new UsernamePasswordAuthenticationToken("AAAAAAAA", "BBBBBBBBBBB");
    	
    	ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
    	HttpServletRequest  request = attrs.getRequest();
    	String sessionId = request.getSession().getId();
    	
    	//SessionRegistry sessionRegistry=(SessionRegistry)applicationContext.getBean("sessionRegistry");
    	
    	SessionRegistry sessionRegistry = applicationContext.getBean(SessionRegistry.class);
    	
    	sessionRegistry.registerNewSession(sessionId, principal);
    	
    	SecurityContextImpl securityContextImpl = new SecurityContextImpl();
    	
//    	Object principal=null; //com.phy.common.security.entity.SysUser@182420e6
//    	Object credentials=null;//Credentials: [PROTECTED];
//    	Authenticated
//    	Authentication authentication = new UsernamePasswordAuthenticationToken(principal, credentials);
//    	
//    	securityContextImpl.setAuthentication(authentication);
    	
        return "accessToken:"+accessToken;
    }
    
}