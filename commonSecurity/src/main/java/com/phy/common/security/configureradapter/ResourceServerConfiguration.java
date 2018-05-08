/*
 * Copyright @ 2018 tivy
 * commonSecurity 2018-05-08 15:24:34
 * All right reserved.
 *
 */
package com.phy.common.security.configureradapter;

import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * @desc: commonSecurity
 * @author: tivy
 * @createTime: 2018-05-08 15:24:34
 * @history:
 * @version: v1.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").authenticated()
//                .anyRequest().authenticated();
        
        http.csrf().disable()
        .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/logout").permitAll()
            .antMatchers("/images/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/fonts/**").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login")
        .defaultSuccessUrl("/")
        .failureUrl("/login?error")
        .permitAll()
        .and()
        .exceptionHandling()
        //.authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\""))
        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
        .and()
        .httpBasic();
        
    }

}