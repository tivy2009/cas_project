package com.lkl.springcloud.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lkl.springcloud.auth.service.impl.UserDetailsServiceImpl;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:16:42
 * ProjectName:Mirco-Service-Skeleton
 */
@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	//org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory
	
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().and()
//                .csrf().disable()
//                .httpBasic();
        
//        http.authorizeRequests()
//        .antMatchers("/","/login","/login?error","/login/**").authenticated()
//        .antMatchers(HttpMethod.POST,"/shop/order").authenticated()
//        .anyRequest().permitAll()
//        .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
//        .and().logout().logoutUrl("/logout").permitAll()
//        .and().csrf().disable().httpBasic();
        
        http
        .authorizeRequests()
            .antMatchers("/", "/home","/login","/login?error","/login/**").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .permitAll()
            .and()
        .logout()
        	.logoutUrl("/logout")
            .permitAll()
            .and()
        .csrf().disable().httpBasic();
        
     
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    	//解决静态资源被拦截的问题
        web.ignoring().antMatchers("/","/login","/login?error","/login/**")
        .antMatchers("/static/**")
        .antMatchers("/css/**")
        .antMatchers("/favor.ioc");
    }

}