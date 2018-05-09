/*
 * Copyright @ 2018 tivy
 * commonSecurity 2018-05-08 15:24:34
 * All right reserved.
 *
 */
package com.phy.common.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * @desc: commonSecurity
 * @author: tivy
 * @createTime: 2018-05-08 15:24:34
 * @history:
 * @version: v1.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "userinfo_rest_api";
    
    @Autowired
    private DataSource dataSource;
    
    @Bean
    public JdbcTokenStore jdbcTokenStore(){
        return new JdbcTokenStore(dataSource);
    }
    
    //声明 ClientDetails实现
    @Bean 
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
    
    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(jdbcTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetails());
        tokenServices.setAccessTokenValiditySeconds(60*60*12); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
        return tokenServices;
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
        resources.tokenServices(defaultTokenServices());
        resources.tokenStore(jdbcTokenStore());
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").authenticated()
//                .anyRequest().authenticated();
        
        http
        .csrf().disable()
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
        .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\""))
        //.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
        .and()
        .httpBasic();
        
//        http.csrf().disable();
//        http.
//        anonymous().disable().authorizeRequests()
//        .antMatchers("/login").permitAll()
//        .antMatchers("/logout").permitAll()
//        .antMatchers("/images/**").permitAll()
//        .antMatchers("/js/**").permitAll()
//        .antMatchers("/css/**").permitAll()
//        .antMatchers("/fonts/**").permitAll()
//        .antMatchers("/favicon.ico").permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .formLogin().loginPage("/login")
//        .defaultSuccessUrl("/")
//        .failureUrl("/login?error")
//        .permitAll()
//        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
////        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
//        .and()
//        .httpBasic();
        
//        http.
//        csrf().disable()
//        .exceptionHandling()
//        .authenticationEntryPoint(new Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\""))
//        .and()
//        .authorizeRequests().anyRequest().authenticated()
//        .and()
//        .httpBasic();
        
    }

}