package com.yellowcong.auth.handler;

import org.apache.commons.lang.StringUtils;
import org.apereo.cas.DefaultMessageDescriptor;
import org.apereo.cas.authentication.AuthenticationException;
import org.apereo.cas.authentication.BasicCredentialMetaData;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.CredentialMetaData;
import org.apereo.cas.authentication.DefaultHandlerResult;
import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.MessageDescriptor;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.Principal;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.web.support.WebUtils;

import com.yellowcong.auth.CustomUsernamePasswordCodeCredential;
import com.yellowcong.auth.constants.Constants;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户名系统认证，只要是admin用户加上sso系统就允许通过
 * @author yellowcong
 * 创建日期:2018/02/06
 *
 */
public class UsernamePasswordCodeAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {
    public UsernamePasswordCodeAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected HandlerResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
    	HttpServletRequest request = WebUtils.getHttpServletRequestFromExternalWebflowContext();
    	String webCode = request.getSession().getAttribute(Constants.STORE_CODE).toString();
    	//当用户名为admin,并且system为sso即允许通过
    	CustomUsernamePasswordCodeCredential codeCredential = (CustomUsernamePasswordCodeCredential) credential;
    	if (StringUtils.isNotBlank(webCode) && webCode.equalsIgnoreCase(codeCredential.getCode())) {
            //这里可以自定义属性数据
        	HandlerResult result = createHandlerResult(credential, this.principalFactory.createPrincipal(((CustomUsernamePasswordCodeCredential) credential).getUsername(), Collections.emptyMap()), null);
        	return result;
        } else {
            //throw new FailedLoginException("验证码错误!");
        	//new DefaultHandlerResult(this, new BasicCredentialMetaData(credential), principal, warnings);
//        	List<MessageDescriptor> warnings = new ArrayList<MessageDescriptor>();
//        	MessageDescriptor message = new DefaultMessageDescriptor("code eror");
//        	warnings.add(message);
//        	return new DefaultHandlerResult(this, new BasicCredentialMetaData(credential), this.principalFactory.createPrincipal(((CustomUsernamePasswordCodeCredential) credential).getUsername(), Collections.emptyMap()), warnings);
        	
        	Map<String, Class<? extends Throwable>> handlerErrors = new HashMap<String, Class<? extends Throwable>>();
        	
        	handlerErrors.put("error code", FailedLoginException.class);
        	
        	Map<String, HandlerResult> handlerSuccesses = new HashMap<String, HandlerResult>();
        	
        	throw new AuthenticationException("error code",handlerErrors,handlerSuccesses);
        	
        }
    }

    @Override
    public boolean supports(Credential credential) {
        return credential instanceof CustomUsernamePasswordCodeCredential;
    }
}