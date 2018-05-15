package com.suncd.demooauth2client.service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.suncd.demooauth2client.servlet.SSOConstant;

public class SsoLoginService {
    
	@SuppressWarnings("rawtypes")
	public String getAccessToken(String code) throws UnsupportedEncodingException
	{
		RestTemplate client = new RestTemplate();
		client.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		Base64 base64 = new Base64();
		final byte[] textByte = SSOConstant.BASIC_AUTH.getBytes("UTF-8");
		final String encodedText = base64.encodeToString(textByte);
		headers.add("Authorization", "Basic "+encodedText);
		
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		params.add("grant_type", "authorization_code");
		params.add("scope", SSOConstant.SCOPE);
		params.add("code", code);
		params.add("redirect_uri", SSOConstant.REDIRECT_URI);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		ResponseEntity<Object> response = client.exchange(SSOConstant.TOKEN_URL, HttpMethod.POST, requestEntity, Object.class);
		if(response !=null && response.getStatusCode().value() == 200)
		{
	        Object result = response.getBody();
	        if(result !=null)
	        {
	            return (String)((Map)result).get("access_token");
	        }
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getUser(String accessToken) throws UnsupportedEncodingException
	{
		String url =  SSOConstant.USERINFO_URL+accessToken;
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		Base64 base64 = new Base64();
		final byte[] textByte = SSOConstant.BASIC_AUTH.getBytes("UTF-8");
		final String encodedText = base64.encodeToString(textByte);
		headers.add("Authorization", "Basic "+encodedText);
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		ResponseEntity<Object> response = client.exchange(url, HttpMethod.POST, requestEntity, Object.class);
		Object result = response.getBody();
		return (Map)result;
	}
	
}
