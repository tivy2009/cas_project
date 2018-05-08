package com.lkl.springcloud.eureka.sso;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SsoLoginService {

	public String getAccessToken(String code) throws UnsupportedEncodingException
	{
		String url = "http://localhost:8081/oauth/token";
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		Base64 base64 = new Base64();
		String authStr = "webApp:webApp";
		final byte[] textByte = authStr.getBytes("UTF-8");
		final String encodedText = base64.encodeToString(textByte);
		System.out.println(encodedText);
		
		headers.add("Authorization", "Basic d2ViQXBwOndlYkFwcA==");
		
		//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		//  也支持中文
		params.add("grant_type", "authorization_code");
		params.add("scope", "app");
		params.add("code", code);
		params.add("redirect_uri", "http://localhost:9092/code");
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		//  执行HTTP请求
		ResponseEntity<Map> response = client.exchange(url, HttpMethod.POST, requestEntity, Map.class);
		//  输出结果
		Map<String, Object> result = response.getBody();
		
		System.out.println(result.toString());
		
		return (String)result.get("access_token");
	}
	
	public Principal getUser(String accessToken) throws UnsupportedEncodingException
	{
		String url = "http://localhost:8081/user?access_token="+accessToken;
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		//  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		Base64 base64 = new Base64();
		String authStr = "webApp:webApp";
		final byte[] textByte = authStr.getBytes("UTF-8");
		final String encodedText = base64.encodeToString(textByte);
		System.out.println(encodedText);
		
		headers.add("Authorization", "Basic d2ViQXBwOndlYkFwcA==");
		
		//  封装参数，千万不要替换为Map与HashMap，否则参数无法传递
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		//  也支持中文
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		//  执行HTTP请求
		ResponseEntity<Principal> response = client.exchange(url, HttpMethod.POST, requestEntity, Principal.class);
		//  输出结果
		Principal result = response.getBody();
		
		System.out.println(result.toString());
		
		return result;
	}
	
}
