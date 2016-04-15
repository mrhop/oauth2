package com.hopever.hope.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Donghui Huo on 2016/3/23.
 */
@SpringBootApplication
@PropertySource(value = { "application.properties","classpath:/application-oauth2-dm.properties" })
public class SpringBootOauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootOauth2Application.class, args);
    }
}
