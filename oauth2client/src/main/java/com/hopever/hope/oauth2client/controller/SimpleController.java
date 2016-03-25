package com.hopever.hope.oauth2client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.security.Principal;

/**
 * Created by Donghui Huo on 2016/3/24.
 */
@Controller
public class SimpleController {

    @Autowired
    @Qualifier("authorWithAuthorizationCodeTemplate")
    private OAuth2RestTemplate authorWithAuthorizationCodeTemplate;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model, Principal principal, HttpServletRequest request) {
        HttpSession a = request.getSession();
        if(principal!=null) {
            String name = principal.getName();
            model.addAttribute("username", name);
        }
        return "index";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index1(ModelMap model, Principal principal) {
        if(principal!=null) {
            String name = principal.getName();
            model.addAttribute("username", name);
        }
        return "index";
    }


    @RequestMapping(value = "/author1", method = RequestMethod.GET)
    @ResponseBody
    public String authorWithAuthorizationCode(ModelMap model, Principal principal) {
        return authorWithAuthorizationCodeTemplate.getForEntity(URI.create("http://localhost:8181/resource/secure"),String.class).getBody();
    }
}
