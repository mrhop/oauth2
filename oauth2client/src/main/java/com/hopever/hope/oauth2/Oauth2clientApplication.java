package com.hopever.hope.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;

/**
 * Created by Donghui Huo on 2016/3/24.
 */
@SpringBootApplication
public class Oauth2clientApplication {
    public static void main(String[] args) {
        UserInfoTokenServices a;
        org.springframework.security.oauth2.client.DefaultOAuth2ClientContext b;
        SpringApplication.run(Oauth2clientApplication.class, args);
    }

}
