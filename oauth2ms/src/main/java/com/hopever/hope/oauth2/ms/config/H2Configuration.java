package com.hopever.hope.oauth2.ms.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Donghui Huo on 2016/3/23.
 */
@Configuration
public class H2Configuration {


    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        WebServlet webServlet = new WebServlet();
        ServletRegistrationBean bean = new ServletRegistrationBean(webServlet);
        bean.addUrlMappings("/h2console/*");
        return bean;
    }
}
