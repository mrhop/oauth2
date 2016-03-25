package com.hopever.hope.oauth2client.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Arrays;

/**
 * Created by Donghui Huo on 2016/3/24.
 */
public class Oauth2Config {




    @Configuration
    @EnableOAuth2Client
    protected static class ResourceConfiguration {

        @Value("${security.oauth2.client.accessTokenUri}")
        private String accessTokenUri;

        @Value("${security.oauth2.client.userAuthorizationUri}")
        private String userAuthorizationUri;


        @Bean
        public OAuth2ProtectedResourceDetails authorWithAuthorizationCode() {
            AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
            details.setId("staff_test");
            details.setClientId("rajithapp2");
            details.setClientSecret("secret");
            details.setAccessTokenUri(accessTokenUri);
            details.setUserAuthorizationUri(userAuthorizationUri);
            details.setScope(Arrays.asList("read", "write"));
            details.setUseCurrentUri(true);
            return details;
        }




        @Bean
        @Qualifier("authorWithAuthorizationCodeTemplate")
        public OAuth2RestTemplate authorWithAuthorizationCodeTemplate() {
            OAuth2ClientContext clientContext = new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest());
            return new OAuth2RestTemplate(authorWithAuthorizationCode(), clientContext);
        }

    }
}
