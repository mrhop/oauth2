package com.hopever.hope.oauth2client.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Donghui Huo on 2016/3/24.
 */
@Configuration
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
                .anyRequest().authenticated().and().logout().logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/index");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web
                .ignoring()
                .antMatchers("/index").antMatchers("/author1");
    }
}
