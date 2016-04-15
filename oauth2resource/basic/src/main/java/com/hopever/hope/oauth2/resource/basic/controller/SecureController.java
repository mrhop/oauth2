package com.hopever.hope.oauth2.resource.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by Donghui Huo on 2016/3/23.
 */
@Controller
@RequestMapping("/secure")
public class SecureController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(Principal principal) {
        return "Secure Hello!";
    }

    @RequestMapping("/user/single")
    @ResponseBody
    public Principal getTrustedUserSingle(Principal principal) {
        return principal;
    }
}
