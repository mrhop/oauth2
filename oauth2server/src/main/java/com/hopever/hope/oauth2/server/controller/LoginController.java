package com.hopever.hope.oauth2.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by Donghui Huo on 2016/3/24.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("username", name);
        return "index";
    }


}
