/*
 * 版权所有 © 成都太阳高科技有限责任公司
 * http://www.suncd.com
 */
package com.suncd.demooauth2server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.suncd.demooauth2server.verificationcode.MyAuthFilter;
import com.suncd.demooauth2server.verificationcode.MyAuthProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    @Bean // share AuthenticationManager for web and oauth
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean  
    public MyAuthFilter myAuthFilter() throws Exception { 
    	RequestMatcher requestMatcher = new AntPathRequestMatcher("/login", "POST");
        MyAuthFilter myAuthFilter = new MyAuthFilter(requestMatcher);  
        myAuthFilter.setAuthenticationManager(authenticationManagerBean());  
        return myAuthFilter;  
    }  
    
    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http.csrf().disable()
        .requestMatchers()
                .antMatchers("/","/login","/oauth/authorize","/oauth/token","/oauth/check_token","/oauth/error")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and().logout()
                .permitAll();
                
        //.usernameParameter("userName").passwordParameter("userPwd")
        http.addFilterBefore(myAuthFilter(), UsernamePasswordAuthenticationFilter.class);  
    } // @formatter:on

    @Autowired  
    private MyAuthProvider authProvider;  
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
//    	auth.authenticationProvider(authProvider);
        auth.parentAuthenticationManager(super.authenticationManagerBean())
                .inMemoryAuthentication()
                .withUser("john")
                .password("123")
                .roles("USER");
    } // @formatter:on

}
