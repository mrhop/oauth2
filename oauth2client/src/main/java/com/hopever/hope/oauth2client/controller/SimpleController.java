package com.hopever.hope.oauth2client.controller;

import com.hopever.hope.util.httpclient.AuthHttpComponentsClientHttpRequestFactory;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;


/**
 * Created by Donghui Huo on 2016/3/24.
 */
@Controller
public class SimpleController {

    @Autowired
    @Qualifier("authorWithAuthorizationCodeTemplate")
    private OAuth2RestTemplate authorWithAuthorizationCodeTemplate;

    @Autowired
    @Qualifier("authorWithClientTemplate")
    private OAuth2RestTemplate authorWithClientTemplate;

    @Autowired
    @Qualifier("authorWithPasswordTemplate")
    private OAuth2RestTemplate authorWithPasswordTemplate;

    @Autowired
    @Qualifier("authorWithImplicitTemplate")
    private OAuth2RestTemplate authorWithImplicitTemplate;

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private AuthorizationCodeAccessTokenProvider authorizationCodeAccessTokenProvider;

    @Resource
    @Qualifier("accessTokenRequest")
    private AccessTokenRequest accessTokenRequest;

    @Autowired
    private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model, Principal principal, HttpServletRequest request) {
        HttpSession a = request.getSession();
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("username", name);
        }
        return "index";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index1(ModelMap model, Principal principal) {
        System.out.println("expiresIn:" + oauth2ClientContext.getAccessToken().getExpiresIn());
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("username", name);
        }
        return "index";
    }


    @RequestMapping(value = "/author1", method = RequestMethod.GET)
    @ResponseBody
    public String authorWithAuthorizationCode(ModelMap model, Principal principal) {
        return authorWithAuthorizationCodeTemplate.getForEntity(URI.create("http://localhost:8181/resource/secure"), String.class).getBody();
    }


    @RequestMapping(value = "/useRefreshToken", method = RequestMethod.GET)
    @ResponseBody
    public String useRefreshToken(ModelMap model, Principal principal) throws URISyntaxException, IOException {
        HttpHost target = new HttpHost("localhost", 9191, "http");
        final AuthHttpComponentsClientHttpRequestFactory requestFactory =
                new AuthHttpComponentsClientHttpRequestFactory(
                        HttpClients.createDefault(), target, "rajithapp1", "secret");
        final RestTemplate restTemplate = new RestTemplate(requestFactory);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", oauth2ClientContext.getAccessToken().getRefreshToken().getValue());
        return restTemplate.postForObject(URI.create("http://localhost:9191/api/oauth/token"), map, String.class);
    }

    @RequestMapping(value = "/useRefreshToken1", method = RequestMethod.GET)
    @ResponseBody
    public String useRefreshToken1(ModelMap model, Principal principal) throws URISyntaxException, IOException {
        return  authorizationCodeAccessTokenProvider.refreshAccessToken(authorizationCodeResourceDetails ,oauth2ClientContext.getAccessToken().getRefreshToken(),accessTokenRequest).getValue();
    }

    @RequestMapping(value = "/usePassword", method = RequestMethod.GET)
    @ResponseBody
    public String usePassword(ModelMap model, Principal principal) throws URISyntaxException, IOException {
       //Object abc = authorWithPasswordTemplate.getAccessToken();
        return authorWithPasswordTemplate.getForObject(URI.create("http://localhost:8181/resource/secure"), String.class);
    }

    @RequestMapping(value = "/useClient", method = RequestMethod.GET)
    @ResponseBody
    public String useClient(ModelMap model, Principal principal) throws URISyntaxException, IOException {
        //Object abc = authorWithPasswordTemplate.getAccessToken();
        return authorWithClientTemplate.getForObject(URI.create("http://localhost:8181/resource/secure"), String.class);
    }

    @RequestMapping(value = "/useImplicit", method = RequestMethod.GET)
    @ResponseBody
    public String useImplicit(ModelMap model, Principal principal) throws URISyntaxException, IOException {
        //Object abc = authorWithPasswordTemplate.getAccessToken();
        return authorWithImplicitTemplate.getForObject(URI.create("http://localhost:8181/resource/secure"), String.class);
    }

    @RequestMapping(value = "/implicit", method = RequestMethod.GET)
    public String useImplicitClient(ModelMap model, Principal principal)  {
        return "implicit";
    }
}
