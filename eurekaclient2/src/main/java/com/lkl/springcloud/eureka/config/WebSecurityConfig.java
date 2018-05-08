package com.lkl.springcloud.eureka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @desc: WebSecurity Configuration类
 * @author: tivy
 * @createTime: 2018-05-03 10:19:49
 * @history:
 * @version: v1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final String LOGIN_URL = "http://localhost:8081/oauth/authorize?client_id=webApp&response_type=code&redirect_uri=http://localhost:9092/code";
	
	private final String FAILURE_URL = "http://localhost:8081/oauth/authorize?client_id=webApp&response_type=code&redirect_uri=http://localhost:9092/code";
	
    @Autowired
    UserDetailsService customUserService;

    @Autowired
    SessionRegistry sessionRegistry;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(rawPassword.toString());
                //return true;
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests()
            //.antMatchers("/login").permitAll()
            .antMatchers("/logout").permitAll()
            .antMatchers("/images/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/fonts/**").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers("/code").permitAll()
            .anyRequest().authenticated()
        .and()
        .formLogin().loginPage(LOGIN_URL)
                    .defaultSuccessUrl("/")
                    .failureUrl(FAILURE_URL)
                    .permitAll()
        .and().sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry)
        .and()
        .and()
        .logout().invalidateHttpSession(true).clearAuthentication(true)
        .and()
        .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        //web.ignoring().antMatchers("/","/login","/login?error","/login/**");
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/favor.ioc");
    }
    
    @Bean
    public SessionRegistry getSessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

}
