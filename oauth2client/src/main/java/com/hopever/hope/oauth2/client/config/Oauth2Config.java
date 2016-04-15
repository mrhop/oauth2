package com.hopever.hope.oauth2.client.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.annotation.Resource;
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

        @Resource
        @Qualifier("accessTokenRequest")
        private AccessTokenRequest accessTokenRequest;


        @Bean
        @Qualifier("authorizationCodeAccessTokenProvider")
        public AuthorizationCodeAccessTokenProvider authorizationCodeAccessTokenProvider() {
            return new AuthorizationCodeAccessTokenProvider();
        }

        @Bean
        public AuthorizationCodeResourceDetails authorWithAuthorizationCode() {
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
        @Qualifier("authorWithAuthorizationCodeClientContext")
        @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
        public OAuth2ClientContext authorWithAuthorizationCodeClientContext() {
            return new DefaultOAuth2ClientContext(accessTokenRequest);
        }

        @Bean
        @Qualifier("authorWithAuthorizationCodeTemplate")
        public OAuth2RestTemplate authorWithAuthorizationCodeTemplate() {
            return new OAuth2RestTemplate(authorWithAuthorizationCode(), authorWithAuthorizationCodeClientContext());
        }

        @Bean
        public ResourceOwnerPasswordResourceDetails authorWithPassword() {
            ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
            details.setId("staff_test");
            details.setClientId("rajithapp");
            details.setClientSecret("secret");
            details.setUsername("admin");
            details.setPassword("admin");
            details.setAccessTokenUri(accessTokenUri);
            details.setScope(Arrays.asList("read", "write"));
            return details;
        }

        @Bean
        @Qualifier("authorWithPasswordClientContext")
        @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
        public OAuth2ClientContext authorWithPasswordClientContext() {
            return new DefaultOAuth2ClientContext(accessTokenRequest);
        }

        @Bean
        @Qualifier("authorWithPasswordTemplate")
        public OAuth2RestTemplate authorWithPasswordTemplate() {
            return new OAuth2RestTemplate(authorWithPassword(), authorWithAuthorizationCodeClientContext());
        }

        @Bean
        public ClientCredentialsResourceDetails authorWithClient() {
            ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
            details.setId("staff_test");
            details.setClientId("rajithapp2");
            details.setClientSecret("secret");
            details.setAccessTokenUri(accessTokenUri);
            details.setScope(Arrays.asList("read", "write"));
            return details;
        }

        @Bean
        @Qualifier("authorWithClientClientContext")
        @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
        public OAuth2ClientContext authorWithClientClientContext() {
            return new DefaultOAuth2ClientContext(accessTokenRequest);
        }

        @Bean
        @Qualifier("authorWithClientTemplate")
        public OAuth2RestTemplate authorWithClientTemplate() {
            return new OAuth2RestTemplate(authorWithClient(), authorWithClientClientContext());
        }


        @Bean
        public ImplicitResourceDetails authorWithImplicit() {
            ImplicitResourceDetails details = new ImplicitResourceDetails();
            details.setId("staff_test");
            details.setClientId("rajithapp3");
            details.setClientSecret("secret");
            details.setAccessTokenUri(accessTokenUri);
            details.setUserAuthorizationUri(userAuthorizationUri);
            details.setScope(Arrays.asList("read", "write"));
            return details;
        }

        @Bean
        @Qualifier("authorWithImplicitClientContext")
        @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
        public OAuth2ClientContext authorWithImplicitClientContext() {
            return new DefaultOAuth2ClientContext(accessTokenRequest);
        }

        @Bean
        @Qualifier("authorWithImplicitTemplate")
        public OAuth2RestTemplate authorWithImplicitTemplate() {
            return new OAuth2RestTemplate(authorWithImplicit(), authorWithImplicitClientContext());
        }


    }
}
