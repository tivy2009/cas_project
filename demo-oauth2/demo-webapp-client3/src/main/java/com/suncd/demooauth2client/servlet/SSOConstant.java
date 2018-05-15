package com.suncd.demooauth2client.servlet;

public class SSOConstant {

    public static String SSO_URL = "http://cas.example.org:8081/auth";
    
    public static String CLIENT_ID = "SampleClientId";
    public static String CLIENT_SECRET = "secret";
    public static String SCOPE = "user_info";
    public static String USERINFO_URI = "/user/me";
    public static String STATE = "lcM5m9";
    
    public static String SUB_APP_URL = "http://discovery2:8084/demo-webapp-client3";
    public static String SUB_APP_INDEX_URI = "/";
    public static String SUB_APP_LOGIN_URI = "/login";
    
    public static String INDEX_URL = SUB_APP_URL + SUB_APP_INDEX_URI;
    public static String REDIRECT_URI = SUB_APP_URL + SUB_APP_LOGIN_URI;
    
    public static String TOKEN_URL = SSO_URL + "/oauth/token";
    public static String USERINFO_URL = SSO_URL + USERINFO_URI + "?access_token=";
    public static String SSO_LOGIN_URL  = SSO_URL + "/oauth/authorize?client_id="+CLIENT_ID+"&response_type=code&redirect_uri="+REDIRECT_URI+"&state="+STATE;
    
    public final static String BASIC_AUTH = CLIENT_ID + ":" + CLIENT_SECRET;
	
}
