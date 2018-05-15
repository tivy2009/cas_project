package com.suncd.demooauth2client.service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.Map;

import javax.servlet.http.Cookie;

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

import com.alibaba.fastjson.JSON;

public class SsoLoginService {
    
    
    
    public final static String token_url = "http://localhost:8081/auth/oauth/token";
    public final static String authStr = "SampleClientId:secret";
    public final static String scope = "user_info";
    public final static String redirect_uri = "http://localhost:8084/demo-webapp-client3/login";
    public final static String userinfo_url = "http://localhost:8081/auth/user/me?access_token=";
    public final static String sso_login_url  = "http://localhost:8081/auth/oauth/authorize?client_id=SampleClientId&response_type=code&redirect_uri=http://localhost:8084/demo-webapp-client3/login&state=lcM5m9";
    
    public final static String index_url = "http://localhost:8084/demo-webapp-client3/";
    
    public String getcode(Cookie[] cookies) throws UnsupportedEncodingException
    {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        Base64 base64 = new Base64();
        
        final byte[] textByte = authStr.getBytes("UTF-8");
        final String encodedText = base64.encodeToString(textByte);
        
        headers.add("Authorization", "Basic "+encodedText);
        
        //  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        //  执行HTTP请求
        ResponseEntity<Map> response = client.exchange(sso_login_url, HttpMethod.GET, requestEntity, Map.class);
        if(response !=null && response.getStatusCode().value() == 200)
        {
            //  输出结果
            Map<String, Object> result = response.getBody();
            
            System.out.println(result.toString());
            if(result !=null)
            {
                return (String)result.get("access_token");
            }
        }
        
        return null;
    }
    
	public String getAccessToken(String code) throws UnsupportedEncodingException
	{
		RestTemplate client = new RestTemplate();
		client.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//		headers.setContentType(type);
		
		//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
		//headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		Base64 base64 = new Base64();
		
		final byte[] textByte = authStr.getBytes("UTF-8");
		final String encodedText = base64.encodeToString(textByte);
		
		headers.add("Authorization", "Basic "+encodedText);
		
		//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		//  也支持中文
		params.add("grant_type", "authorization_code");
		params.add("scope", scope);
		params.add("code", code);
		params.add("redirect_uri", redirect_uri);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		//  执行HTTP请求
		ResponseEntity<Object> response = client.exchange(token_url, HttpMethod.POST, requestEntity, Object.class);
		if(response !=null && response.getStatusCode().value() == 200)
		{
		    //  输出结果
	        Object result = response.getBody();
	        System.out.println(result.getClass().getName());
	        System.out.println(result.toString());
	        if(result !=null)
	        {
	            return (String)((Map)result).get("access_token");
	        }
		}
		
		return null;
	}
	
	public Map getUser(String accessToken) throws UnsupportedEncodingException
	{
		String url = userinfo_url+accessToken;
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		Base64 base64 = new Base64();
		final byte[] textByte = authStr.getBytes("UTF-8");
		final String encodedText = base64.encodeToString(textByte);
		headers.add("Authorization", "Basic "+encodedText);
		
		//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		//  也支持中文
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		//  执行HTTP请求
		ResponseEntity<Object> response = client.exchange(url, HttpMethod.POST, requestEntity, Object.class);
		//  输出结果
		Object result = response.getBody();
		System.out.println(result.getClass().getName());
		System.out.println(result.toString());
		return (Map)result;
	}
	
}
