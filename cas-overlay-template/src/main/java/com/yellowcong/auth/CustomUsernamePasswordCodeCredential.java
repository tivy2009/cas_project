package com.yellowcong.auth;

import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;

public class CustomUsernamePasswordCodeCredential extends RememberMeUsernamePasswordCredential{

	private static final long serialVersionUID = 5034129937759981063L;  
	
    private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}  
	
}
