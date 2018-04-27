package com.yellowcong.auth.handler;

import java.security.GeneralSecurityException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apereo.cas.authentication.Credential;
import org.apereo.cas.authentication.HandlerResult;
import org.apereo.cas.authentication.PreventedException;
import org.apereo.cas.authentication.exceptions.AccountDisabledException;
import org.apereo.cas.authentication.exceptions.AccountPasswordMustChangeException;
import org.apereo.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.web.support.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.yellowcong.auth.CustomUsernamePasswordCodeCredential;
import com.yellowcong.auth.constants.Constants;

/**
 * 用户名 密码 验证码认证
 * @author tivy
 * 创建日期:2018/02/06
 *
 */
public class UsernamePasswordCodeAuthenticationHandler extends AbstractPreAndPostProcessingAuthenticationHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsernamePasswordCodeAuthenticationHandler.class);
	
	public UsernamePasswordCodeAuthenticationHandler(String name, ServicesManager servicesManager, PrincipalFactory principalFactory, Integer order) {
        super(name, servicesManager, principalFactory, order);
    }

    @Override
    protected HandlerResult doAuthentication(Credential credential) throws GeneralSecurityException, PreventedException {
    	LOGGER.info("authentication user login staring.");
    	CustomUsernamePasswordCodeCredential codeCredential = (CustomUsernamePasswordCodeCredential) credential;
    	HttpServletRequest request = WebUtils.getHttpServletRequestFromExternalWebflowContext();
    	String webCode = request.getSession().getAttribute(Constants.STORE_CODE).toString();
    	//自定义返回的属性
    	final Map<String, Object> attributes = new LinkedHashMap<>();
        final String username = codeCredential.getUsername();
        final String password = codeCredential.getPassword();
    	
    	//验证验证码是否正确
        if (StringUtils.isBlank(webCode) || !webCode.equalsIgnoreCase(codeCredential.getCode()))
        {
        	LOGGER.info("Validate Code is failed.");
        	throw new FailedLoginException("Validate Code is failed.");
        }
    	
    	//验证用户名密码是否正确
        String sql="select * from cms_auth_user where user_name=?";
        DriverManagerDataSource d=new DriverManagerDataSource();
        d.setDriverClassName("com.mysql.jdbc.Driver");
        d.setUrl("jdbc:mysql://127.0.0.1:3306/castest");
        d.setUsername("root");
        d.setPassword("123456");
        JdbcTemplate template=new JdbcTemplate();
        template.setDataSource(d);
        Map<String,Object> userMap =template.queryForMap(sql, new Object[]{username});
        if(userMap == null || !password.equalsIgnoreCase((String)userMap.get("password")))
        {
        	LOGGER.info("Password does not match value on record.");
        	throw new FailedLoginException("Password does not match value on record.");
        }
        
    	//验证用户状态是否可用
        String status = (String)userMap.get("status");
        if(status !=null && ("N".equalsIgnoreCase(status) || "false".equalsIgnoreCase(status) || "0".equalsIgnoreCase(status)))
        {
        	LOGGER.info("Account has been disabled.");
        	throw new AccountDisabledException("Account has been disabled");
        }
    	
    	//验证密码是否过期
        String expired = (String)userMap.get("expired");
        if(expired !=null && ("Y".equalsIgnoreCase(expired) || "true".equalsIgnoreCase(expired) || "1".equalsIgnoreCase(expired)))
        {
        	LOGGER.info("Password has expired.");
        	throw new AccountPasswordMustChangeException("Password has expired");
        }
    	
        return createHandlerResult(credential, this.principalFactory.createPrincipal(((CustomUsernamePasswordCodeCredential) credential).getUsername(), attributes), null);
    	
    }

    @Override
    public boolean supports(Credential credential) {
        return credential instanceof CustomUsernamePasswordCodeCredential;
    }
}